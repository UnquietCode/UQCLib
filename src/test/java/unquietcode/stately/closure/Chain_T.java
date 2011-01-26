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

		ClosureView<String> spacer = (new AbstractClosure1<String, String>() {
			String subjects[] = {"ball", "tomato", "car", "Rubik's cube"};
			public @Override String run(String s) {
				return s + " ";
			}
		}).toClosure();


		// Now let's combine them into a chain which makes sentences!
		Chain<String> sentenceGen = new Chain<String>(articleGen, subjectGen, verbGen, articleGen, objectGen);
		String sentence = sentenceGen.run("");
		out(sentence);

		// Pretty gross. Let's add a spacer. //TODO if it were a real collection this is the place to show it
		sentenceGen = new Chain<String>(articleGen, spacer, subjectGen, spacer,
										verbGen, spacer, articleGen, spacer, objectGen);
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
