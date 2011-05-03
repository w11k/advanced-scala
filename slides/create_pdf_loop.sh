#! /bin/sh

iwatch -c "pdflatex -output-directory=. advanced-scala.tex" -e modify -t ".*tex" .

