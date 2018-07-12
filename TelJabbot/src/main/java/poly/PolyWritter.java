package poly;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.polly.AmazonPollyClient;
import com.amazonaws.services.polly.AmazonPollyClientBuilder;
import com.amazonaws.services.polly.model.OutputFormat;
import com.amazonaws.services.polly.model.SynthesizeSpeechRequest;
import com.amazonaws.services.polly.model.SynthesizeSpeechResult;
import com.amazonaws.services.polly.model.VoiceId;
import utilities.Config;
import utilities.PathSystem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by nikit on 2018/02/22.
 */
public class PolyWritter {

    static BasicAWSCredentials awsCreds;
    static SynthesizeSpeechRequest tssRequest;
    static AmazonPollyClient apClient;
    public PolyWritter() {
        awsCreds = new BasicAWSCredentials(Config.F_POLY_KEY, Config.S_POLY_KEY);

        apClient = (AmazonPollyClient) AmazonPollyClientBuilder.standard()
                        .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                        .withRegion(Regions.EU_WEST_1).build();
        tssRequest = new SynthesizeSpeechRequest();
    }

    public static void createReadingFile(String text) throws Exception {

        System.out.println("creating file "+text+".mp3");

        tssRequest.setText(text);
        tssRequest.setVoiceId(VoiceId.Mizuki);
        tssRequest.setOutputFormat(OutputFormat.Mp3);

        try (FileOutputStream outputStream = new FileOutputStream(new File(PathSystem.JAP_AUDIOS_DIR+"/"+text+".mp3"))) {
            SynthesizeSpeechResult synthesizeSpeechResult = apClient.synthesizeSpeech(tssRequest);
            byte[] buffer = new byte[2 * 1024];
            int readBytes;

            try (InputStream in = synthesizeSpeechResult.getAudioStream()){
                while ((readBytes = in.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, readBytes);
                }
            }
        } catch (Exception e) {
            System.err.println("Exception caught: " + e);
        }
    }

}
