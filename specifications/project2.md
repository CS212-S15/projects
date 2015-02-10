Project 2 - TF-IDF Scoring and Search
==========================

*tentative and subject to change*

For this project, you will modify your InvertedIndex to include a`search` method that will take as input a (possibly multi-word) query and will return a list of documents matching the query. The list will be sorted according to the [Term Frequency-Inverse Document Frequency](http://www.tfidf.com/) score calculated by your program.

##Requirements##

**Configuration** - Your `config.json` file may contain two additional parameters:

1. `searchPath` - If specified, the value associated with this key specifies a relative path to a file containing queries, one per line. Each query may contain one *or more* words. Your program will execute each query by invoking the `search` method of the `InvertedIndex` once the entire index has been built.
2. `searchOutputPath` - If specified, the results of each query will be written to the file specified by the value associated with this key. 

**Results Output** - Your output must be formatted as the example below. In this example, there were three 	queries. The query `aa` produced no results, the query `t` produced one result, and the query `elephant antelope` produced two results.

```
aa

t
input/simple/symbols.txt

elephant antelope
input/simple/capitals.txt
input/simple/symbols.txt
```

**TF-IDF Scoring** -  If a query is found in more than one document, the list of resulting documents will be ordered using TF-IDF scoring. The *term frequency (TF)* score for a single word in a single document is the number of times the word appears in the document divided by the total number of words in the document. The *inverse document frequency (IDF)* score for a single word in a single document is the log of the total number of documents divided by the number of documents containing the word. An example follows:

```
Document 1:
my computer is faster than your computer

Document 2:
i love computer science

Document 3:
science is fun

```
```
Query: computer

TF(computer, D1):
# occurrences of computer in D1 = 2
# words in D1 = 7
2/7 = .286

IDF(computer):
total documents = 3
documents containing computer = 2
log(3/2) = .176

TF-IDF(computer, D1) = .286*.176 = .05

TF(computer, D2):
1/4 = .25

IDF(computer): .176

TF-IDF(computer, D2) = .25*.176 = .044

```
The score for a multi-word query should be the sum of the scores for each word, for example the score for "computer science" in D2 would be .044+.044. In this case, it just happens that the score for both words is the same.


##Hints##
 Here are a few hints based on my solution. You do not need to use this design to pass the test cases.
 
 1. In your `InvertedIndex`, maintain a data member that maps the documents seen to the number of words in each document. This will allow you to implement methods in `InvertedIndex` to return the total number documents and the total words in a document given the document name.
 2. Implement methods `calculateTFIDF` in both `InvertedIndex` and `DocumentLocationMap`. 
 3. Implement a custom data structure to maintain the score results for a word. My `InvertedIndex` `calculatedTFIDF` method returns a `DocumentResultList`, which is a query and a sorted list of `DocumentResult` objects. Each `DocumentResult` contains a document (String) and a score.
 4. Implement a separate class to read in the queries from the file and execute them on the `InvertedIndex`.
 
##Submission##
Additional test cases will be provided for Project 2 and you must pass all of the original Project 1 test cases along with the Project 2 test cases in order to qualify for code review.

Follow these instructions *carefully* in order to submit your project: [Project Guidelines](https://github.com/CS212-S15/lectures/blob/master/Notes/projectguidelines.md)
