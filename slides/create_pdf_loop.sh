#! /bin/sh

iwatch -c "pdflatex -output-directory=output as.tex" -e modify -t ".*tex" .

