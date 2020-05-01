# Contributing to Voodoo2D
Welcome to Voodoo2D! If you are new to the GitHub and open source world, we recommend you to take a look at this [10 minute read introduction](https://guides.github.com/activities/hello-world/).

#### Table Of Contents
1. [Getting the project](#getting-the-project)
   - [git](#git)
   - [GitHub Desktop](#github-desktop)
   - [Source tree](#source-tree)
2. [Setting up the project](https://github.com/CremBluRay/voodoo2d/wiki/Cloning-Voodoo2D)
3. [The Process of Making Changes](#the-process-of-making-changes)

## Getting the project
First, you need to make a copy of this repository to be able to modify it on your side. This is called a fork. To fork Voodoo2D, simply go to the [repository page](https://github.com/crembluray/voodoo2d) and click on the **Fork button** <img src="https://user-images.githubusercontent.com/17777237/54873012-40fa5b00-4dd6-11e9-98e0-cc436426c720.png" height="14"/> located at the top right corner. 

Then, you will need to download [git](https://git-scm.com/downloads). Git is a version-control system for tracking changes in source code. It is used through the command line or third-party software.

Refer to [the wiki](https://github.com/CremBluRay/voodoo2d/wiki/Cloning-Voodoo2D) for full detail on this step.

### Make a choice
Here are three options for software to use git. If you want to get started without too much hassle and command lines, then we strongly suggest using GitHub Desktop or Source tree. Otherwise, if you really like command lines, then check out the instructions for git.

If you are using IntelliJ IDEA, however, just make sure you have git installed on your system and follow [this guide](https://github.com/CremBluRay/voodoo2d/wiki/Cloning-Voodoo2D)

|<a href="#git"><img src="https://camo.githubusercontent.com/74f5b1f94719773966dfb3a0c2703f13b803e3d8/68747470733a2f2f6769742d73636d2e636f6d2f696d616765732f6c6f676f732f646f776e6c6f6164732f4769742d49636f6e2d426c61636b2e706e67" title="git setup instructions" alt="git" width="100"/></a>|<a href="#github-desktop"><img src="https://desktop.github.com/images/desktop-icon.svg" title="GitHub Desktop setup instructions" alt="GitHub Desktop" width="100"/></a>|<a href="#source-tree"><img src="https://img.stackshare.io/service/1599/sourcetree.png" title="Source tree setup instructions" alt="Source tree" width="100"/></a>|
|:---:|:---:|:---:|
|[git](#git)|[GitHub Desktop](#github-desktop)|[Source tree](#source-tree)|

## git
You have a fork of the project on your GitHub account (the cloud). Now you need to get a local version on your computer. Here is how to do so:
1. Open command prompt if you are on Windows or open Terminal if you are on a mac
4. Navigate to where you want your project to be cloned. In command prompt, type ```cd directory/to/where/you/want/your/project``` for example: ```cd C:/Users/Noodleman123/Desktop/Programming/Java```
5. To clone the project, execute ```git clone https://github.com/your-github-username/voodoo2d/``` a valid url could be: ```https://github.com/Noodleman123/voodoo2d/```
6. Execute ```cd voodoo2d``` to go in the project directory
7. Execute ```git checkout master``` to have the right files, the ones that are being developed.

Here is a summary of all the commands:
```
cd directory/to/where/you/want/your/project
git clone https://github.com/your-github-username/voodoo2d/
cd voodoo2d
git checkout master
```

### Refresh
Every now and then, it is a good practice to sync your local version to the cloud version `origin`. This is particularly useful to stay up to date and avoid merge conflicts:
```
git pull
```

### Save on your local version control (git)
You should execute these 2 commands every time you complete a task:
```
git add .
git commit -m "a description telling what you modified/added"
```
The first command adds everything that has been modified to the history of your local version control. The second command will group the changes in a commit with a description.

### Save in the cloud (GitHub)
You should save your local work on your forked GitHub when you accomplish something. If your computer breaks, at least your code will be safe in the cloud 👍
```
git push
```
That's it for the git setup! Now, you are ready to [open up the code editor](wiki). Don't forget to come back to this file when you are ready to know about [the Process of Making Changes](#the-process-of-making-changes).

## GitHub Desktop
Download [GitHub Desktop](https://desktop.github.com/)


| Step | Screenshot |
| --- | --- |
| Follow the installation wizard  | <img src="https://i.imgur.com/aKMckCx.png" width="400"/> |
| Click on **Clone a repository<br> from the internet...** | <img src="https://i.imgur.com/KxqSh9j.png" width="400"/> |
| Select your fork of **voodoo2d**,<br> navigate to where you want the<br> project to be cloned and **Clone** | <img src="https://i.imgur.com/5rx8vvr.png" width="400"/> |
| Choose the `master` branch or the<br> branch you want to contribute to | <img src="https://i.imgur.com/Dvib6hA.png" width="200"/> |

### Refresh
Every now and then, it is a good practice to sync your local version to the cloud version `origin`. This is particularly useful to stay up to date and avoid merge conflicts:  
  
<img src="https://i.imgur.com/m7hoCYD.png" width="200"/>

### Save your contribution
It is recommended to create a commit every time you complete a task:

| Step | Screenshot |
| --- | --- |
| At the bottom left, fill in the summary and<br> the description box (if necessary).<br> Then press on **commit to master**.<br> This will create a commit in<br> history on your local computer. | <img src="https://i.imgur.com/pLfNs3Q.png" width="300"/> |
| To update your commit to your fork,<br> simply press on **Push origin** | <img src="https://i.imgur.com/RZ9hBqD.png" width="300"/> |

That's it for the GitHub Desktop setup! Now, you are ready to [open up the code editor](wiki). Don't forget to come back to this file when you are ready to know about [the Process of Making Changes](#the-process-of-making-changes).

## Source tree
Download [Source tree](https://www.atlassian.com/software/sourcetree)

> **Warning** [Two-factor authentication](https://help.github.com/en/github/authenticating-to-github/configuring-two-factor-authentication) is required for your GitHub account to continue...

| Step | Screenshot |
| --- | --- |
| You need to create a Bitbucket Cloud account<br> to use Source tree. But you have the option<br> to connect with Google or Microsoft. | <img src="https://i.imgur.com/E04JvOd.png" width="200"/> |
| Follow the installation wizard | <img src="https://i.imgur.com/M4Mdty9.png" width="300"/> |
| Uncheck Mercurial, we only need git. | <img src="https://i.imgur.com/Cr7j4h2.png" width="300"/> |
| Click on **Add an account...** | <img src="https://i.imgur.com/orYhkNZ.png" width="300"/> |
| Set the hosting service to **GitHub**,<br> set the Preferred Protocol to **HTTPS**,<br> set Authentication to **OAuth**,<br> and click on **Refresh OAuth Token** | <img src="https://i.imgur.com/5Gwd4nE.png"  width="300"/> |
| Link your GitHub account to Source tree | <img src="https://i.imgur.com/8TIrysz.png"  width="100"/> |
| Select voodoo2d and click on **Clone** | <img src="https://i.imgur.com/ANmdJ4x.png" width="300"/> |
| Navigate to where you want to clone the project<br> and **Clone** | <img src="https://i.imgur.com/NISlqY3.png" width="300"/> |

### Refresh
Every now and then, it is a good practice to sync your local version to the cloud version `origin`. This is particularly useful to stay up to date and avoid merge conflicts:  
  
<img src="https://i.imgur.com/mQBwjb5.png" width="50"/>

### Save your contribution
It is recommended to create a commit every time you complete a task:

| Step | Screenshot |
| --- | --- |
| Add the files you modified to the staged files<br> by clicking on the plus. Only staged files will<br> be included in the commit. | <img src="https://i.imgur.com/cklbKBi.png" width="25"/> |
| Write a description and **commit**.<br> This will create a commit in history on your<br> local computer. | <img src="https://i.imgur.com/8ywlZQf.png" width="200"/> |
| To update your commit to your fork, simply<br> press on **Push** | <img src="https://i.imgur.com/5DqCEXY.png" width="50"/> |

That's it for the Source tree setup! Now, you are ready to [open up the code editor](wiki). Don't forget to come back to this file when you are ready to know about [the Process of Making Changes](#the-process-of-making-changes).

## The Process of Making Changes
### Issues
You are now ready to contribute! Github represents tasks as issues. You can find all of the issues [here](https://github.com/crembluray/voodoo2d/issues) or in the **Issues** tab of the repository. If you found a bug, would like to add a feature or simply do not find something interesting to contribute to, then you can [create your own issue](https://github.com/crembluray/voodoo2d/issues/new).

### Pull request
A pull request is the final step to contribute something. A request should be made when you complete a "milestone" or resolve an issue. Here is how to open one:

| Step | Screenshot |
| --- | --- |
| Go to the [Voodoo2D GitHub repository](https://github.com/crembluray/voodoo2d) and click on Pull requests | <img src="https://i.imgur.com/YMSuIvf.png" width="100"/> |
| Click on the big green Pull request button | <img src="https://i.imgur.com/0taUoAY.png" width="100"/> |
| Click on **compare across forks**. The Head repository should be your fork, and the Base should be `CremBluRay/voodoo2d`. Be sure to choose the right repository and the correct branch which is usually `master`. | <img src="https://i.imgur.com/QRFl63b.png"/> |
| Add a title, a description and tags (if necessary). | <img src="https://i.imgur.com/WU9hJ3g.png" width="100"/> |
| Your pull request will be reviewed by a collaborator shortly | 🎉🎉 |