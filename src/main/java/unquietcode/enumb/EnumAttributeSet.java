/*
 * Copyright $YEAR$ Benjamin Fagin
 *
 * This work is licensed under the Common Development and Distribution License (CDDL), Version 1.0
 *
 * Read the included LICENSE.TXT for more information.
 *
 * --------------------------------------------------------------------
 */

package unquietcode.enumb;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * EnumSets and EnumMaps are great, but they don't allow the kind of free-for-all mixing of
 * enums that can make for an interesting creation. What if you want an object to have color=blue,
 * size=10, and other object to have size=4, canFly=maybe? Well, now that's possible.
 *
 * //TODO setup a hierarchy where the base level addAttribute, if it returns false
 * means whatever. So that let's say there's a property stored and I try to add an attribute to it,
 * it will fail to do so, until the property has been removed.
 *
 *
 * @author Ben
 * @version 1.0
 * Date: Dec 2, 2010
 */
public class EnumAttributeSet {
	Set<Enum> m_attributes = new HashSet<Enum>();
	Map<Enum, Object> m_data = new HashMap<Enum, Object>();
	Map<Type, Enum> m_classMap = new HashMap<Type, Enum>();

	//boolean attribute

	/**
	 *
	 * @param attribute
	 * @param unlessProperty if true, the attribute will remove property efsdsd
	 * @return
	 */
	public boolean addAttribute(Enum attribute, boolean unlessProperty) {
		if (unlessProperty)
			return addAttributeUnlessProperty(attribute);
		else
			return addAttribute(attribute);
	}


	public boolean addAttributeUnlessProperty(Enum attribute) {
		if (attribute == null)
			return false;

		if (m_classMap.containsKey(attribute.getDeclaringClass()))
			return false;

		return m_attributes.add(attribute);
	}

	public boolean addAttribute(Enum attribute) {
		if (attribute == null)
			return false;

		//make sure that attributes aren't already properties
		Class theClass = attribute.getDeclaringClass();
		if (m_classMap.containsKey(theClass) && m_classMap.get(theClass) != attribute)
			m_classMap.remove(theClass);
		
		return m_attributes.add(attribute);
	}


	public boolean removeAttribute(Enum attribute, boolean unlessProperty) {
		if (unlessProperty)
			return removeAttributeUnlessProperty(attribute);
		else
			return removeAttribute(attribute);
	}

	public boolean removeAttributeUnlessProperty(Enum attribute) {
		if (attribute == null)
			return false;

		//if it's a different attribute we wouldnt be able to remove it anyway
		if (m_classMap.containsKey(attribute.getDeclaringClass()))
			return false;

		return m_attributes.remove(attribute);
	}

	public boolean removeAttribute(Enum attribute) {
		if (attribute == null)
			return false;

		Class theClass = attribute.getDeclaringClass();
		if (m_classMap.containsKey(theClass) && m_classMap.get(theClass) == attribute)
			m_classMap.remove(theClass);
		
		return m_attributes.remove(attribute);
	}


	public boolean hasAttribute(Enum attribute) {
		if (attribute == null)
			return false;

		return m_attributes.contains(attribute);
	}

	public boolean is(Enum attributee) {    //alias
		return hasAttribute(attributee);
	}



	//stored with data
	public <V, T extends Enum & Castable<V>> boolean addData(T attribute, V data) {
		if (attribute == null)
			return false;

		if (addAttribute(attribute))
			return !(attribute == m_data.put(attribute, data));
		else
			return false;
	}

	@SuppressWarnings("unchecked")
	public <V, T extends Enum & Castable<V>> V getData(T attribute) {
		return (V) m_data.get(attribute);
	}

	public <T extends Enum> boolean removeData(T attribute) {
		m_data.remove(attribute);
		return removeAttribute(attribute);
	}



	//typed attribute
	//whereas above is to say "am I priority x?, am I priority y?
	//these say "what is my priority, if any"
	//which will be the last associated priority.
	//but then this will be weird if it has stored high, and low and then low again

	public boolean hasProperty(Class enumClass) {
		if (!enumClass.isEnum())
			throw new IllegalArgumentException("Class must be an enum type.");

		return m_classMap.containsKey(enumClass);
	}

	public boolean setProperty(Enum property) {
		if (property == null)
			return false;
		
		Class theClass = property.getDeclaringClass();
		m_classMap.put(theClass, property);

		//remove all others
		Enum list[] = (Enum[]) theClass.getEnumConstants();
		for (Enum e : list) {
			if (e != property)
				removeAttribute(e);
		}

		return addAttribute(property);
	}

	public boolean removeProperty(Class enumClass) {
		if (!enumClass.isEnum())
			throw new IllegalArgumentException("Class must be an enum type.");

		return removeAttribute(m_classMap.remove(enumClass));
	}

	public Enum getProperty(Class enumClass) {
		if (!enumClass.isEnum())
			throw new IllegalArgumentException("Class must be an enum type.");

		return m_classMap.get(enumClass);
	}



	
	public interface Castable<T> {
		T cast(Object o);
	}

	
}
