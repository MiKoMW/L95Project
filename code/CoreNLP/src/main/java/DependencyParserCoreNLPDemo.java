import edu.stanford.nlp.parser.nndep.DependencyParser;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.AnnotationPipeline;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.PropertiesUtils;
import edu.stanford.nlp.util.logging.Redwood;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Demonstrates how to use the NN dependency
 * parser via a CoreNLP pipeline.
 *
 * @author Christopher Manning
 */
public class DependencyParserCoreNLPDemo {

    /** A logger for this class */
    private static final Redwood.RedwoodChannels log = Redwood.channels(DependencyParserCoreNLPDemo.class);

    private DependencyParserCoreNLPDemo() {} // static main method only

    public static void main(String[] args) throws IOException {
        String text;
//        if (args.length > 0) {
//            text = IOUtils.slurpFileNoExceptions(args[0], "utf-8");
//        } else {
//            text = "I can almost always tell when movies use fake dinosaurs.";
//        }
        long startTime = System.nanoTime();


        Properties props = PropertiesUtils.asProperties(
                "annotators", "tokenize,ssplit,pos,depparse",
                "depparse.model", DependencyParser.DEFAULT_MODEL
        );

        AnnotationPipeline pipeline = new StanfordCoreNLP(props);



        ArrayList<String> sts = FileIO.readLine("/Users/macbookpro/Desktop/data/all.txt");
        int con = 0;
        for(String st :sts){
            Annotation ann = new Annotation(st);
            pipeline.annotate(ann);

//            for (CoreMap sent : ann.get(CoreAnnotations.SentencesAnnotation.class)) {
//                SemanticGraph sg = sent.get(SemanticGraphCoreAnnotations.BasicDependenciesAnnotation.class);
//
//                log.info(IOUtils.eolChar + sg.toString(SemanticGraph.OutputFormat.LIST));
//                System.out.println(IOUtils.eolChar + sg.toString(SemanticGraph.OutputFormat.LIST));
//                System.out.println(IOUtils.eolChar + sg.toString());
////            GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
////            GrammaticalStructure.printDependencies(gs, gs.typedDependencies(), parse, true, false);
////            System.out.println(GrammaticalStructureConversionUtils.dependenciesToCoNLLXString((Collection<TypedDependency>) sg,sent));
//            }
            con+=1;
//            OutputStream outputStream = new FileOutputStream(new File(con + ".out"));
//            CoNLLOutputter.conllPrint(ann,outputStream);
        }
        long endTime = System.nanoTime();

        long duration = (endTime - startTime)/1000000;  //divide by 1000000 to get milliseconds.

        System.out.println(duration);





    }

}