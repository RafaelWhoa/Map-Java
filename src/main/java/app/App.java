package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
  *     Na contagem de votos de uma eleição, são gerados vários registros
  * de votação contendo o nome do candidato e a quantidade de votos
  * (formato .csv) que ele obteve em uma urna de votação. Você deve
  * fazer um programa para ler os registros de votação a partir de um
  * arquivo, e daí gerar um relatório consolidado com os totais de cada
  * candidato.
  *
  **/
public class App 
{
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter file full path: ");
        String path = sc.nextLine();

        Map<String, Integer> votes = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            
            String line = br.readLine();
            while(line != null) {

                String[] tokens = line.split(",");
                String candidate = tokens[0];
                int votesCount = Integer.parseInt(tokens[1]);

                if (votes.containsKey(candidate)) {
                    int candidateVotes = votes.get(candidate);
                    votes.put(candidate, candidateVotes + votesCount);
                }
                else {
                    votes.put(candidate, votesCount);
                }
                line = br.readLine();
            }

            for (String string : votes.keySet()) {
                System.out.println(string + ": " + votes.get(string));
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}
