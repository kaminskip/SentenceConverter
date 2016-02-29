## Synopsis

Program for converting text into XML or CSV. Text file is broken into sentences 
and words and words are sorted. 

## Motivation

My first approach was creating an application without using modern frameworks and tools, just pure java. But I decided 
to implement more extendable, professional solution using most suitable tools: Spring Boot, Spring Batch.

## Requirements

Java JDK 1.8
Gradle 2.5

## Run program

Edit application.properties (setup input, output and output type)

For *NIX systems run SentenceConverter.sh

For Windows run SentenceConverter.sh

## Tests

To check application just run:

gradle test 

## Tests

To build application with all libraries inside:

gradle bootRepackage

## Author

Paweł Kamiński
