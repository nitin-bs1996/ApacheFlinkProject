package com.pluralsight.flink;
import java.util.Set;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;
public class FilterMovies {
    public static void main(String[] args) throws Exception {
    ExecutionEnvironment env=ExecutionEnvironment.getExecutionEnvironment();
    DataSet<Tuple3<Long, String, String>> lines = env.readCsvFile("./input_files/movies.csv")
            .ignoreFirstLine()
            .parseQuotedStrings('"')
            .ignoreInvalidLines()
            .types(Long.class,String.class,String.class);
    DataSet<Movie> movies = lines.map(new MapFunction<Tuple3<Long, String, String>, Movie>() {
        @Override
        public Movie map(Tuple3<Long, String, String> csvLine) throws Exception {
            String movieName =csvLine.f1;
            String[] genres = csvLine.f2.split("\\|");
            return new com.pluralsight.flink.Movie(movieName,new java.util.HashSet<>(java.util.Arrays.asList(genres)));
        }
    });
    DataSet<Movie> filteredMovies= movies.filter(new org.apache.flink.api.common.functions.FilterFunction<Movie>() {
        @Override
        public boolean filter(com.pluralsight.flink.Movie movie) throws Exception {
            return movie.getGenres().contains("Drama");
        }
    });
        filteredMovies.writeAsText("./output_files/filtered-output/");
        env.execute();
    }
}

class Movie {
    private String name;
    private Set<String> genres;
    public Movie(String name,Set<String> genres){
        this.name = name;
        this.genres = genres;
    }

    public String getName(){
        return name;
    }
    public Set<String> getGenres(){
        return genres;
    }
    @Override
    public String toString(){
        return "Movie{"+"name='"+name+"\'"+", genres="+genres+"}";
    }
}