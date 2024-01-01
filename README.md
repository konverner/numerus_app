# Numerus: Numbers in Languages

## Introduction 

This is an android application for language learning designed to improve one's oral comprehension of numbers. It uses YouTube content via
official API to extract samples of pronunciation of numbers with corresponding subtitles which than exposed to user.
The app does NOT require any permissions (can be found in the `AndroidManifest.xml`)

This application is released under GNU GPLv3 (see `LICENSE`). Some of the used libraries are released under different licenses.

## Full Application Discription

Oral comprehension of numbers in foreign languages can be challenging. Here's why:

1. Frequency of numbers in ordinary discourse is usually low. So, a learner struggle to develop a habit to listen to numbers.

2. The most of words are easy to recognize due to context, whereas numbers are isolated. For example, 

*With a population of 8 804 190, New York City is the most populated city in US.*

The context helps only a little bit, one can make a guess that it is in the range from 1 to 10 millions. An educated guess can narrow this range to 8 millions but still thousands place is essentially random. 

3. Some languages have different ways to construct numerals. For example, how to say 95 in some languages:

English: _ninety five (90 5)_
German: _fünfundneunzig (5 and 90)_
French: _quatre-vingt-quinze (4-20-15)_

So, The best way to train oneself to comprehend numbers in speech is to expose oneself to the frequent repetition of numbers in various contexts. This is where this application comes in.

The idea is the following: user choose language then

1. Random 10 seconds clip from video begins to play. In this clip, some number is pronounced. After the end of the clip it repeats.
2. The user writes number that he hears then he clicks check button.
3. Subtitles of the clip show up, the system says whether the user's answer is right or wrong.
4. The user clicks the next button, go to 1.
