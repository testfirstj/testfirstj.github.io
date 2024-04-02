CSSDIR=css
IMAGESDIR=images
.SUFFIXES:
.SUFFIXES: adoc html svg

%.html: %.adoc
	asciidoctor -b html5 -a linkcss -a imagesdir=$(IMAGESDIR) -a stylesdir=$(CSSDIR)  -r asciidoctor-html5s \
	-a stylesheet=asciidoctor.css -a topics=../topics $< -o $(@F)

all: assessmentcorrectionrules.html setup.html index.html tips.html \
	week01.html week02.html week03.html week04.html week05.html week06.html \
	week07.html week08.html week09.html week10.html week11.html week12.html


VPATH=../sources/src/docs/asciidoc

include depfile
#index.html: ../sources/docs/index.adoc

