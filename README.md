# Roman Numerals

The purposes of this assignment are:
* building up your skills with the tools we are using this semester, including git, GitHub, JUnit, and Eclipse
* letting you practice test-driven development
* giving you a challenging small project that introduces some interesting issues we will explore in class

## Part 0. Setup (30 minutes)

Clone the repository and import it into Eclipse [[video instructions](https://mills.instructure.com/courses/1074/pages/importing-a-github-classroom-repo-into-eclipse)]. Add a second `@author` tag with your name beneath mine. Generate the javadoc [[video instructions](https://mills.instructure.com/courses/1074/pages/generating-javadoc-in-eclipse)]. Read through the code and javadoc. Ask me about anything you don't understand.

The following steps [video instructions] will walk you through creating a new branch, committing and pushing your changes, and creating a pull request for me to review.

### Add the doc directory to .gitignore
If you do:
```
git status
```
you will see your modification to `RomanNumeral.java` and the addition of the `doc` directory. You may also see minor changes to `.project` or `.classpath`.

Automatically generated files should usually *not* be committed (see [discussion](https://softwareengineering.stackexchange.com/questions/391804/should-generated-documentation-be-stored-in-a-git-repository)), so we want to exclude these from git.

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

### Add .gitignore to the staging area

The following three commands all do the same thing, if done in the root (top) directory of your repo (which is where you should generally be in your terminal window):
```
git add .          # Stage all added, changed, or modified files in this directory or its subdirectories.
git add --all      # Stage all added, changed, or modified files in the repo.
git add -A         # Stage all added, changed, or modified files in the repo.
```

Since we're adding only a few files, you can specify them by name or directory:
```
git add .gitignore
git add src        # Add your changes in the src directory and its subdirectories.
```

If you make subsequent changes to these files, you need to re-add them for those changes to be staged and later committed.

### Commit your changes
To commit your changes (move them from the staging area to the local permanent record), do:
```
git commit
```
This will open an editor and prompt you to write a commit message. It will also show you what changes will be committed. If all looks good, type the commit message, then save and exit the editor. If you want to cancel the commit, you can do so in most editors by typing ^C (control-c) or ^Z (control-z) and by declining the offer to save changes to the file, if offered.

If you prefer, you can specify the commit message on the command line:
```
git commit -m "Add my name as author"
```
We will follow these [commit message guidelines](https://chris.beams.io/posts/git-commit/). You don't need to mention the addition to `.gitignore` and any other files whose names start with `,` (unless you include them in separate commits), since those changes are relatively insignificant.

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
In general, the command-line argument "-v", which stands for "verbose", gives more detailed information for different commands.

### Create a pull request

Please put comments about any problems you encountered in the pull request.

## Part 1. Create unit tests (2 hours)

Create a new branch named `tests` for creating JUnit5 unit tests. 

Write unit tests for the methods `convertFromString()` and `convertToText()`, providing both valid and invalid arguments, in which case you will need to check for exceptions. You should have 3-5 test methods for each of the two methods under test, and some should use parameterized tests. At least one test for each method under test should use `@CsvSource`, as described at [Guide to JUnit 5 Parameterized Tests | baeldung](https://www.baeldung.com/parameterized-tests-junit-5).

I encourage you to make use of (and add) links on the Canvas site.

You can choose how often to commit and push changes to your repository.

When you are done, make a pull request from your `tests` branch to your `main` branch, assigned to me.

## Part 2: Implement the methods (4-6 hours)

Your job is to implement `convertFromString()` and `convertToText()`. I recognize that those of you who are less experienced may not be able to complete this task by Thursday, September 10, and you are not expected to make a heroic effort to do so. (You should be spending 8-10 hours/week outside of classtime.) 

If you can't implement everything in this time, you may:
* Implement only one of the two methods.
* Reduce `MAX_VALUE` (for example, only accept numbers up to 99, instead of 9999).
* Do not implement the rule in which a smaller symbol in front of a larger symbol indicates subtraction (for example, treating "IX" as 11 instead of 9). If you do not implement everything, modify the javadoc accordingly.

You should create at least one new branch for this work. (If you want to try multiple approaches, you might choose to use multiple branches.) 

Working with classmates is encouraged, and a pair of students may turn in a single assignment if they do all of the work together. Contact me to discuss logistics.

Be sure to commit your code before moving to the next part. (You will probably make multiple commits.)

## Part 3: Check code coverage (5-30 minutes)

As shown in this video, check the code coverage in your tests. Note what your coverage is at this point.

If your coverage of the two test methods is below 100%, write more tests. 

## Submitting work (30 minutes)

You must submit your work to me by noon, Thursday, September 10. The way to submit it is by making a pull request from your implementation branch to your main branch and assigning it to me. We will look at code together in class. If you are not willing for your code to be shown in front of the class, let me know that in your pull request.

You should include comments in your pull request indicating:
* about how long you spent on the assignment
* whether you worked with anyone
* what your test coverage was at the beginning and end of part 3
* how effective your tests were at finding bugs
* what you think of test-driven development


