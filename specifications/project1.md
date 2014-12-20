Project 1 - Inverted Index
==========================

*tentative and subject to change*

For this project, you will write a Java program that recursively processes all text files in a directory and builds an inverted index to store a mapping from words to the documents (and positions within those documents) where the words were found. For example, suppose we have the following two documents:

```
birdstory.txt: The cat ate the bird.
catstory.txt: The cat ran.
```
The inverted index will contain the following mapping:

```
the
"birdstory.txt", 1, 4
"catstory.txt", 1

cat
"birdstory.txt", 2
"catstory.txt", 2

ate
"birdstory.txt", 3

bird
"birdstory.txt", 5

ran
"catstory.txt", 3
```

##Requirements##

**API Implementation** - You must implement the *exact* API provided for this assignment. All required methods must be implemented, though you may add additional helper methods as necessary. You also *may* add additional classes, however keep in mind that efficiency will be considered when evaluating your work. 

**Configuration** - Your program will read relevant configuration information from a file called `config.json` stored in the directory where the program is run. The format of the file will be as follows where the value of the key `inputPath` specifies the path of the directory to traverse and the value of the key `digitDelimiter` must be `true` or `false`, `true` if the program should use digits as a delimiter in processing the file and `false` otherwise.

```
{
	"inputPath": "input/gutenberg",
	"outputFile": "output/result.txt"
	"digitDelimiter": false
}
```
`inputPath` is required and specifies the path of the directory to traverse.

`outputFile` is optional and specifies the name of the file where output should be directed. If no `outputFile` is specified then no output is required by the program.

`digitDelimiter` is a required boolean value where `true` indicates that the program should use digits as a delimiter in processing the input files and `false` otherwise. If the value is true, then anything *except* upper and lower-case letters should be considered a delimiter. If the value is false then anything *except* upper and lower-case letters and digits should be considered a delimiter. For example, if `digitDelimiter` is `true` then `a b3c*de` would be parsed into the tokens `a`, `b`, `c`, and `de`. If it is `false` then the same string would be parsed into the tokens `a`, `b3c`, and `de`.

**Directory Traversal** - Recursively traverse a directory and process all text files found in the directory or its subdirectories. Any file ending in the `.txt` extension (case insensitive!) should be considered a text file. 

**Data Structure** - Store the mapping built during directory traversal in the custom `InvertedIndex` data structure specified in the required API.

**Output** - If an `outputFile` is specified your program will store the resulting `InvertedIndex` in the file in alphabetically sorted order using the following output format:

```
apple
"relative/path/to/file1.txt", 11, 15

banana
"relative/path/to/file1.txt" 1, 22
"relative/path/to/file2.txt" 2, 4
```

The word is listed alone on a single line, followed by lines with the relative file path and a comma-separated list of locations. An empty line separates entries. The words are in sorted order, the files are sorted by path name, and the line numbers are sorted numerically. It is *extremely important* that you follow this exact format.

**More Details** - Make sure to adhere to all of the following in order to pass all unit tests.

1. File names should be stored as the normalized relative path. Do not use the absolute path.
2. The first word in a file is at position 1.
3. Use a `Scanner` to read the file a token at a time. When instantiating the `Scanner`, specify the delimiter to use based on the configuration information provided in `config.json`. (If you read from the file a line at a time I'll know that you're looking at the code of a student from a previous semester!)
4. Words must be case insensitive, for example apple, Apple, APPLE, and aPpLE are all the same word.
5. Thorough error checking is required and will be verified via the tests provided. Make sure to check for cases where files are not found, JSON is incorrectly formatted, and so on.
6. Your main class must be named `Driver`.

##Submission##

TBD

