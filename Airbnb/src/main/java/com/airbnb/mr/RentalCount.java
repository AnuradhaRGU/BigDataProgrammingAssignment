package com.airbnb.mr;



import java.io.IOException;
import java.util.*;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class RentalCount {

    public static class WordCountMapper extends Mapper<Object, Text, Text, IntWritable> {

        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();
        final String DELIMITER = ",";

        public void map(Object key, Text value, Context context)  throws IOException, InterruptedException {
             String line = value.toString();
            if(line.toString().startsWith("id,name")){
                // Skip header line (first line) of CSV
                return;
            }
            StringBuilder sb = new StringBuilder();
            String[] tokens = value.toString().split(DELIMITER);
            int totalError=0;
            for(int i=0; i< tokens.length;i++){
                String token = tokens[i];
                int totalLength = token.length();
                token=token.trim();
                token = token.replaceAll("^\"|\"$", "");
                totalError+=(totalLength-token.length());
                sb.append(token);
                if(i<tokens.length-1)
                    sb.append(DELIMITER);
            }

            line=sb.toString();
            int calculatedhostListing=0;
            String data[] = line.toString().split(",", -1);
            if(data.length==16) {
                if(data[14].trim()!="")
                    calculatedhostListing= Integer.parseInt(data[14]);
                else
                    calculatedhostListing=1;

                String rentals = data[15];

                if( Integer.parseInt(data[15]) ==365) {
                    word.set(rentals);
                    one.set(1);
                    context.write(word, one);
                }
           }

        }
    }

    public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

        private IntWritable result = new IntWritable();

        public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }

            result.set(sum);

            context.write(key, result);

        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        String[] otherArgs = new GenericOptionsParser(conf, args)
                .getRemainingArgs();
       // if (otherArgs.length != 2) {
        //    System.err.println("Usage: WordCount <in> <out>"+args[2]);
       //    System.exit(2);
       // }
        Job job = Job.getInstance(conf, "word count");
        job.setJarByClass(RentalCount.class);
        job.setMapperClass(WordCountMapper.class);
        job.setCombinerClass(IntSumReducer.class);
        job.setReducerClass(IntSumReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path(args[1]));
        FileOutputFormat.setOutputPath(job, new Path(args[2]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

}
