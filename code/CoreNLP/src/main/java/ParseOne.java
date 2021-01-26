import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.parser.nndep.DependencyParser;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.AnnotationPipeline;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.PropertiesUtils;
import edu.stanford.nlp.util.logging.Redwood;

import java.io.IOException;
import java.util.Properties;

/**
 * Demonstrates how to use the NN dependency
 * parser via a CoreNLP pipeline.
 *
 * @author Christopher Manning
 */
public class ParseOne {

    /** A logger for this class */
    private static final Redwood.RedwoodChannels log = Redwood.channels(DependencyParserCoreNLPDemo.class);

    private ParseOne() {} // static main method only

    public static void main(String[] args) throws IOException {
        String text;
//        if (args.length > 0) {
//            text = IOUtils.slurpFileNoExceptions(args[0], "utf-8");
//        } else {
//            text = "I can almost always tell when movies use fake dinosaurs.";
//        }


        Properties props = PropertiesUtils.asProperties(
                "annotators", "tokenize,ssplit,pos,depparse",
                "depparse.model", DependencyParser.DEFAULT_MODEL
        );

        AnnotationPipeline pipeline = new StanfordCoreNLP(props);

        String st = "He tried to ignore what his own common sense told him, but it wasn’t possible; her motives were too blatant.";
        st = "It consists of a series of pipes and a pressure-measuring chamber which record the rise and fall of the water surface.";
        st = "He tried to ignore what his own common sense told him.";
                Annotation ann = new Annotation(st);
            pipeline.annotate(ann);
//
            for (CoreMap sent : ann.get(CoreAnnotations.SentencesAnnotation.class)) {
                SemanticGraph sg = sent.get(SemanticGraphCoreAnnotations.BasicDependenciesAnnotation.class);

                log.info(IOUtils.eolChar + sg.toString(SemanticGraph.OutputFormat.LIST));
                System.out.println(IOUtils.eolChar + sg.toString(SemanticGraph.OutputFormat.LIST));
                System.out.println(IOUtils.eolChar + sg.toString());
//            GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
//            GrammaticalStructure.printDepen`
//            dencies(gs, gs.typedDependencies(), parse, true, false);
//            System.out.println(GrammaticalStructureConversionUtils.dependenciesToCoNLLXString((Collection<TypedDependency>) sg,sent));
            }
//            OutputStream outputStream = new FileOutputStream(new File( "temp.out"));
//            CoNLLOutputter.conllPrint(ann,outputStream);
        }



}