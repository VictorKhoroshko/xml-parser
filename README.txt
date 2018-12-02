The application that compares two HTML/XML files to find similar elements defined by an id attribute in the source file.

To run the app use the following command

    java -jar xml-parser.jar <absolute-path-to-source-file> <absolute-path-to-target-file> [<element-id>]

<element-id> is optional, if not specified 'make-everything-ok-button' is used.

Example:

    java -jar xml-parser.jar X:/sample-0-origin.html X:/sample-1-evil-gemini.html

Result:

Target element path #root[0] > html[0] > body[1] > div[0] > div[1] > div[2] > div[0] > div[0] > div[1] > a[1]
Overall similarity is 62.18 percent
Attributes similarity {onclick=1.0, rel=0.5, href=0.23076923076923078, id=0.0, title=1.0, class=1.0}

A number in brackets in the path is element's sibling index (e.g. div[2]).

Results of execution on the provided test set:

1) sample-1-evil-gemini.html

Target element path #root[0] > html[0] > body[1] > div[0] > div[1] > div[2] > div[0] > div[0] > div[1] > a[1]
Overall similarity is 62.18 percent
Attributes similarity {onclick=1.0, rel=0.5, href=0.23076923076923078, id=0.0, title=1.0, class=1.0}

2) sample-2-container-and-clone.html

Target element path #root[0] > html[0] > body[1] > div[0] > div[1] > div[2] > div[0] > div[0] > div[1] > div[0] > a[0]
Overall similarity is 71.74 percent
Attributes similarity {onclick=0.8666666666666667, rel=1.0, href=1.0, id=0.0, title=1.0, class=0.4375}

3) sample-3-the-escape.html

Target element path #root[0] > html[0] > body[1] > div[0] > div[1] > div[2] > div[0] > div[0] > div[2] > a[0]
Overall similarity is 69.70 percent
Attributes similarity {onclick=1.0, rel=1.0, href=1.0, id=0.0, title=0.18181818181818182, class=1.0}

4) sample-4-the-mash.html

Target element path #root[0] > html[0] > body[1] > div[0] > div[1] > div[2] > div[0] > div[0] > div[2] > a[0]
Overall similarity is 81.11 percent
Attributes similarity {onclick=0.8666666666666667, rel=1.0, href=1.0, id=0.0, title=1.0, class=1.0}


