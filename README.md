# TopHeadlines


a desktop console application for parsing JSON data of newsapi.org API.

App. displays TOP-HEADLINES of world news (US is default).


check DEFAULTS class (At the top) to change to your desired country 
and to put your api-key to be able to use it

get free api-key from newsapi.org ... 


Loading the JSON was done Asynchronously in a background thread, then was parsed after retrieval.
Observer Design Pattern was used for this purpose.

and the library json-simple was added and used for parsing.

links / guides :-
1. https://www.tutorialspoint.com/json/json_java_example.htm
2. https://code.google.com/archive/p/json-simple/
3. https://www.geeksforgeeks.org/asynchronous-synchronous-callbacks-java/
4. https://newsapi.org/

<img src="https://user-images.githubusercontent.com/48130426/62246914-59fec280-b3ed-11e9-9f63-ea00d75770dc.png" width=600 height=400/>&emsp;<img src="https://user-images.githubusercontent.com/48130426/62246915-59fec280-b3ed-11e9-9f89-477ef3f43b71.png" width=600 height=400/>
