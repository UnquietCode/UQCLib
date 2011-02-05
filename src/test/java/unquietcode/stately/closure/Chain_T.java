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

import org.junit.Test;
import unquietcode.stately.closure.view.ClosureView;

import java.util.Random;

import static unquietcode.util.Shortcuts.out;

/**
 * @author  Benjamin Fagin
 * @version 01-25-2011
 */
public class Chain_T {
	@Test
	public void basic() {
		// Chains allow multiple closures to be linked together into one execution sequence.

		final Random gen = new Random();  // shared generator

		ClosureView<String> articleGen = (new AbstractClosure1<String, String>() {
			String articlesAndPronouns[] = {"a", "the", "that", "this"};
			public @Override String run(String s) {
				String val = articlesAndPronouns[gen.nextInt(articlesAndPronouns.length)];
				return s + val;
			}
		}).toClosure();

		ClosureView<String> subjectGen = (new AbstractClosure1<String, String>() {
			String subjects[] = {"boy", "girl", "dog", "cat"};
			public @Override String run(String s) {
				String val = subjects[gen.nextInt(subjects.length)];
				return s + val;
			}
		}).toClosure();

		ClosureView<String> verbGen = (new AbstractClosure1<String, String>() {
			String verbs[] = {"ate", "destroyed", "threw", "chased", "ignored"};
			public @Override String run(String s) {
				String val = verbs[gen.nextInt(verbs.length)];
				return s + val;
			}
		}).toClosure();

		ClosureView<String> objectGen = (new AbstractClosure1<String, String>() {
			String subjects[] = {"ball", "tomato", "car", "Rubik's cube"};
			public @Override String run(String s) {
				String val = subjects[gen.nextInt(subjects.length)];
				return s + val;
			}
		}).toClosure();


		// Now let's combine them into a chain which makes sentences!
		Chain<String> sentenceGen = new Chain<String>(articleGen, subjectGen, verbGen, articleGen, objectGen);
		String sentence = sentenceGen.run("");
		out(sentence);


		// Pretty gross. Let's add a spacer.
		ClosureView<String> spacer = (new AbstractClosure1<String, String>() {
			String subjects[] = {"ball", "tomato", "car", "Rubik's cube"};
			public @Override String run(String s) {
				return s + " ";
			}
		}).toClosure();

		for (int x : new int[]{1,3,5,7}) {
			sentenceGen.insert(x, spacer);
		}

		sentence = sentenceGen.run("");
		out(sentence);


		// Let's add some punctuation to the end at least. We can easily do that with Chain.append
		ClosureView<String> punctuator = (new AbstractClosure1<String, String>() {
			String subjects[] = {"ball", "tomato", "car", "Rubik's cube"};
			public @Override String run(String s) {
				return s + (gen.nextBoolean() ? "." : "!");
			}
		}).toClosure();

		sentenceGen.append(punctuator);
		sentence = sentenceGen.run("");

		// (Why not capitalize it too while we're at it.)
		if (sentence.length() > 0) {
			sentence = Character.toUpperCase(sentence.charAt(0)) + sentence.substring(1);
		}

		// And that's how chains work!
		out(sentence);
	}

	@Test
	public void nullTest() {
		//TODO
	}


}
