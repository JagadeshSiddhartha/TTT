This is a submission for the TTT contest.
In this project, I have used Java Servlets and JSP since I have recently learnt them.
I used default methods for making http requests. I stored all the words in the page as an array of strings, then used a hashmap to count the occurances of eac word.
I then sorted the hashmap based on the "vales" and printed the top most "n" used words.

<b>Note: I have made a small assumption.</b>
<i>If there are multiple words with the same word count, then all of them get the same rank.</i>
<br/>
<i>If the word count is like</i> {"hi" : 3, "hello" : 2, "there" : 2, "TTT" : 1}<i>, then the top two words are</i> {"hi", "hello", "there"}.
