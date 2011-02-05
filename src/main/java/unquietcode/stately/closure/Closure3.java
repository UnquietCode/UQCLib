/*******************************************************************************
 Copyright 2009-2011 Benjamin Fagin

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.


 Read the included LICENSE.TXT for more information.
 ******************************************************************************/

package unquietcode.stately.closure;

import unquietcode.stately.closure.view.Closure3View;

/**
 * @author Benjamin Fagin
 * @version 01-23-2011
 */
public interface Closure3<Z, A,B,C> extends ClosureInterfaceBase<Z> {
	Z run(A p1, B p2, C p3);
	Closure3View<Z, A,B,C> getView();
	Class[] getArgumentTypes();
}
