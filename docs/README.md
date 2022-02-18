# User Guide: Morty - Task Manager

This is a personal assistant by the name Morty, inspired by and adapted after the eponymous sci-fi character from the show Rick and Morty, who stores, and manages tasks for you. While the Rick and Morty theme plays well in familiarising the user, Morty has none of the memory flaws of their namesake!

![Ui](https://thewrik.github.io/ip/Ui.png)

## Prerequisites
This program uses Java11. [Download java](https://java.com/en/download/help/download_options.html) to proceed if your computer does not have it installed. 

*Note: While certain other versions of Java may be sufficient to run the program, you may not receive the full suite of features.*
## Setup Instructions
**GUI**
1. Download the `Morty.jar` from [here](https://github.com/thewrik/ip/releases/tag/A-Release)
2. Locate the download folder and double click on `Morty.jar` to run the bot.

**CLI**
1. Open your terminal.
2. Use this command:
  `java -jar relativePath/Morty.jar`
where `relativePath` is a placeholder for the relative path of `Morty.jar` with respect to your current directory location.

## Features

* CREATE, READ, DELETE functionality for tasks
* 3 Types of tasks supported - Todo, Deadline, Event
* Tasks can be marked as completed, or unmarked, if not
* Task List can be viewed
* Task list saved after every action
* User errors such as **incorrect date formats** and deadline or event tasks
without **valid dates** checked
* Tasks can be searched for using keywords matching a word,
  or a part thereof in the task body

## Usage

### `list` - Display all tasks

Generates a list of all tasks that exist.

Example output: 
```
You have 4 tasks in the list
1. [T][X] borrow book
2. [D][ ] read book (by: Jan 20)
3. [E][ ] book club meeting (at: Jan 22)
4. [D][ ] return book (by: Jan 25)
```
   

### `todo` - Add a todo task 

Passed along with a string that forms the task body. 

Example: `todo borrow book`

Morty responds with the following outcome
```
Great, have added the following task for you:
[T][ ] borrow book
Now you have 1 task.
```


### `event` - Add an event

Passed along with a string that forms the task body, as well as a time
parameter in the `DD-MM-YYYY`form, separated by `/at`.

Example: `event book club meeting /at 05/01/2022`

Morty responds with the following outcome

```
Great, have added the following task for you:
[E][ ] book club meeting (at: Jan 05, 22)
Now you have 3 tasks.
```

In case the date is missed, or incorrect, the user is informed as follows

```
An Event Task must have an an associated event time.
Please try again!
```
### `deadline` - Add a deadline

Passed along with a string that forms the task body, as well as a time
parameter in the `DD-MM-YYYY`form, separated by `/by`.

Example: `deadline return book /by 01/08/2022`

Morty responds with the following outcome
```
Great, have added the following task for you:
[D][ ] return book (by: Jan 08, 22)
Now you have 4 tasks.
```

The standard error checks apply.

### `delete` - Delete a task

Command passed with a serial number as a parameter, indicating the task number 
that is to be deleted.

Example: `delete 3`

Morty responds with the following outcome, returning the task that was 
deleted as well as the number of remaining tasks.
``` 
Noted. I have removed the following task:
[E][ ] book club meeting (at: Jan 05, 22)
You now have 3 tasks
```

### `mark` - Mark a task as done

Command passed with a serial number as a parameter, indicating the task number
that is to be marked as completed.

Example `mark 1`

Morty responds with the following outcome, returning the task that was marked
completed.
```
Well done! I have marked this task as done.
[T][X] borrow book
```

### `unmark` - Unmark a task, i.e. record it as yet to be done

Command passed with a serial number as a parameter, indicating the task number
that is to be marked as not yet complete.

Example `unmark 2`

Morty responds with the following outcome, returning the task that was marked not yet complete
```
No worries, I have unmarked this task, good luck!
[D][ ] read book (by: Jan 05, 20)
```

### `find` - Search for tasks with a given keyword

Command passed with a keyword that is searched through all the `tasks` in the list, returning those that contain
the `taskKeyword` in question as one of the constituent words

Example  `find book`

```
Here are the tasks found with the given keyword.
1. [T][X] borrow book
2. [E][ ] book club meeting (at: Jan 08, 22)
3. [D][ ] read book (by: Jan 05, 20)
```
### `findBetter` - Search for tasks with a given keyword including tasks which contain the keyword as a proper substring of a constituent word

Command passed with a keyword that is searched through all the `tasks` in the list, returning those that contain
the `taskKeyword` in question as one of the constituent words **or** as a substring of the constituent words

Example  `findBetter book`

```
Here are the tasks found with the given keyword.
1. [T][X] borrow book
2. [E][ ] book club meeting (at: Jan 08, 22)
3. [D][ ] read book (by: Jan 05, 20)
4. [T][ ] make booking for club room
```

### `bye` - Exit the bot

Simply terminates processing with a farewell message.

Example `bye`

```
Hope you had fun talking to me, and bye!
Have a nice day ahead!
```

