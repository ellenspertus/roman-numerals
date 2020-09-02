# roman-numerals

The purposes of this assignment are:
* building up your skills with the tools we are using this semester
* letting you practice test-driven development
* giving you a challenging small project that introduces some interesting issues we will explore in class

## Part 0. Setup

Clone the repository and import it into Eclipse [[video instructions](https://mills.instructure.com/courses/1074/pages/importing-a-github-classroom-repo-into-eclipse)]. Add a second `@author` tag with your name beneath mine. Generate the javadoc [[video instructions](https://mills.instructure.com/courses/1074/pages/generating-javadoc-in-eclipse)]. Read through the code and javadoc. Ask me about anything you don't understand.

The following steps [video instructions] will walk you through creating a new branch, committing and pushing your changes, and creating a pull request for me to review.

### Add the doc directory to .gitignore
If you do:
```
git status
```
you will see many new files in the `doc` directory. Automatically generated files should usually *not* be committed (see [discussion](https://softwareengineering.stackexchange.com/questions/391804/should-generated-documentation-be-stored-in-a-git-repository)).

Open and edit the `.gitignore` file in the root directory of the repo. Add this line to the end:
```
doc/
```
to exclude the `doc` directory and its subdirectories. Make sure to press `Enter` at the end of the line. It's a [good practice to end files with a newline character (empty line)](https://unix.stackexchange.com/questions/18743/whats-the-point-in-adding-a-new-line-to-the-end-of-a-file).

### Create and checkout the branch

You can create the branch and check it out separately:
```
git branch setup       # Create branch.
git checkout setup     # Check out branch.
```
I prefer to do them at the same time, because (1) I'm lazy and (2) I don't risk forgetting to check out the branch after creating it.
```
git checkout -b setup  # Create and check out branch.
```

### See what files have changed

These commands will be helpful:
```
git status
git diff
```

### Add files to the staging area

The following three commands all do the same thing, if done in the root (top) directory of your repo (which is where you should generally be in your terminal window):
```
git add .          # Stage all added, changed, or modified files in this directory or its subdirectories.
git add --all      # Stage all added, changed, or modified files in the repo.
git add -A         # Stage all added, changed, or modified files in the repo.
```

If you wanted to stage only the files in the `src` directory, you could do:
```
cd src             # Change the current directory to the src subdirectory.
git add .          # Add all files in this directory or its subdirectories.
cd ..              # Change the current directory back to the parent directory.
```
or:
```
git add src        # Add all files in the src directory or its subdirectories.
```
I prefer the latter because (1) I'm lazy and (2) I don't forget to `cd` back to the root directory.

### Commit your changes
To commit your changes (move them from the staging area to the local permanent record), do:
```
git commit
```
This will open an editor and prompt you to write a commit message. It will also show you what changes will be committed. If all looks good, type the commit message, then save and exit the editor. If you want to cancel the commit, type ^C (control-c) or ^Z (control-z).

If you prefer, you can specify the commit message on the command line:
```
git commit -m "Add javadoc"
```
We will follow these [commit message guidelines](https://chris.beams.io/posts/git-commit/).

### Push your changes to GitHub

The first time you push your changes for a new branch, you need to specify the remote (which has the name `origin`) and the branch on the remote. I always use the same name for the local branch and remote branch.  If you are lazy, do:
```
git push
```
This will suggest the correct command, which is:
```
git push --set-upstream origin setup
```
There is a shorter version:
```
git push -u origin setup
```

You can see your repo's remotes with the command:
```
git remote             # Show the names of the remotes.
```
or:
```
git remote -v          # Show the names and URLs of the remotes.
```
In general, the command-line argument "-v", which stands for "verbose", gives more detailed information. Try using it with other commands, such as `git status`.

### Create a pull request

