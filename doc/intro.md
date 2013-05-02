# Introduction to rn-all

rn-all is a tool that renames all directories and files in a given location and replaces strings by a given set of patterns.
In essence, rn-all generates bash script with rename and sed commands.
To generate the script you must supply patterns and a directory you want start renaming.
By default renaming directory is current.

Patterns are written as OldName>NewName:OLD_NAME>NEW_NAME

For example,

$ cat OldName>NewName:OLD_NAME>NEW_NAME | rn-all ~/my-projects/OldName >rename-all.sh