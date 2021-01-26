import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.parser.nndep.DependencyParser;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.util.logging.Redwood;

import java.io.IOException;
import java.io.StringReader;
import java.util.Collection;
import java.util.List;

/**
 * Demonstrates how to first use the tagger, then use the NN dependency
 * parser. Note that the parser will not work on untagged text.
 *
 * @author Jon Gauthier
 */
public class DependencyParserDemo  {

    /** A logger for this class */
    private static final Redwood.RedwoodChannels log = Redwood.channels(DependencyParserDemo.class);

    private DependencyParserDemo() {} // static main method only

    public static void main(String[] args) throws IOException {
        String modelPath = DependencyParser.DEFAULT_MODEL;
        String taggerPath = "edu/stanford/nlp/models/pos-tagger/english-left3words-distsim.tagger";

//        for (int argIndex = 0; argIndex < args.length; ) {
//            if ("-tagger".equals(args[argIndex])) {
//                taggerPath = args[argIndex + 1];
//                argIndex += 2;
//            } else if ("-model".equals(args[argIndex])) {
//                modelPath = args[argIndex + 1];
//                argIndex += 2;
//            } else {
//                throw new RuntimeException("Unknown argument " + args[argIndex]);
//            }
//        }

        String text = "I can almost always tell when movies use fake dinosaurs.";

        MaxentTagger tagger = new MaxentTagger(taggerPath);
        DependencyParser parser = DependencyParser.loadFromModelFile(modelPath);

        DocumentPreprocessor tokenizer = new DocumentPreprocessor(new StringReader(text));
        for (List<HasWord> sentence : tokenizer) {
            List<TaggedWord> tagged = tagger.tagSentence(sentence);
            GrammaticalStructure gs = parser.predict(tagged);
            Collection tdl = gs.typedDependenciesCollapsed();
//
//            System.out.println("words: "+words);
//            System.out.println("POStags: "+tags);
//            System.out.println("stemmedWordsAndTags: "+stems);
            System.out.println("typedDependencies: "+tdl);
        }
    }

}