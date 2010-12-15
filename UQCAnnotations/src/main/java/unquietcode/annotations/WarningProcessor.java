package unquietcode.annotations;

import com.sun.source.util.TreePath;
import com.sun.source.util.Trees;

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.*;
import javax.tools.Diagnostic.Kind;


/**
 * @author Ben
 * @version 0.1
 *          Date: 12/11/10
 */
@SupportedAnnotationTypes("unquietcode.annotations.Warning")
public class WarningProcessor extends AbstractProcessor {

	private ProcessingEnvironment env;
	private Trees trees;

	@Override
	public synchronized void init(ProcessingEnvironment pe) {
		super.init(pe);
		trees = Trees.instance(pe);
		this.env = pe;
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		if (!roundEnv.processingOver()) {
			for (TypeElement te : annotations) {
				final Set< ? extends Element> elements = roundEnv.getElementsAnnotatedWith(te);
				for (Element e : elements) {
					//so in theory, if e is a method then we need to find all uses of e in the compilation
					TreePath tp = trees.getPath(e);
					env.getMessager().printMessage(Kind.NOTE, tp.toString());

					Warning w = e.getAnnotation(Warning.class);
					env.getMessager().printMessage(Kind.WARNING, String.format(" %s : %s ", e, w.value()));
				}
			}
		}
		return true;
	}

}


