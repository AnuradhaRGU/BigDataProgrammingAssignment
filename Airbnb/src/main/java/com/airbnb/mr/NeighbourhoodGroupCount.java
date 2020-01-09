package com.airbnb.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;

public class NeighbourhoodGroupCount {


    public static class WordCountMapperNe extends Mapper<Object, Text, Text, IntWritable> {

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
                token =token.replace("\n", " ");
                totalError+=(totalLength-token.length());
                sb.append(token);
                if(i<tokens.length-1)
                    sb.append(DELIMITER);
            }

            line=sb.toString();
            String data[] = line.toString().split(",", -1);
            if(data.length==16) {
                String neighbourhood_group = data[4];
                word.set(neighbourhood_group);
                one.set(1);

            }

            context.write(word, one);

        }
    }

    public static class IntSumReducerNe extends Reducer<Text, IntWritable, Text, IntWritable> {

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
         //   System.err.println("Usage: WordCount <in> <out>"+args[0]);
         //   System.exit(2);
        // }
        Job job = Job.getInstance(conf, "negi");
        job.setJarByClass(NeighbourhoodGroupCount.class);
        job.setMapperClass(WordCountMapperNe.class);
        job.setCombinerClass(IntSumReducerNe.class);
        job.setReducerClass(IntSumReducerNe.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path(args[1]));
        FileOutputFormat.setOutputPath(job, new Path(args[2]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

}
