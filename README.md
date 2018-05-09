# Syntel-Website
Training website for Syntel


#    GITHUB INSTRUCTIONS

## Command Line
	
### Clone:
	1) Click on the green button that is labeled "Clone or Download" and copy the link shown.

	2) Open a terminal window or Git Bash window on your machine and "cd" to wherever you want this project to live.

	3) "git clone <link>"
		This will clone the project repository to your local machine.

	4) "cd Syntel-Website"
		You should now be in the parent project folder.

	NOTE:  This is a one-time setup.  Once you have the repo, you don't need to ever reclone it.

### Workflow:
	1) "git checkout master"
		This will put you on the 'master' branch of the repository.

	2) "git pull origin master"
		This will update your local repository with any of the changes made from others on the same project.

	3) Make changes to your local project repository (new folders, new files, edit files, delete files, etc.)

	4) "git commit -m 'Some type of description of your changes here.'"
		This will update your local repository's history with your current changes.
		Always remember to add a commit message.

	5) "git push origin master"
		This will update the remote branch with your changes to be seen and pulled by everyone else on the project.

	NOTE:  As long as you are within the project repo and have already done Step 1 (checkout).
			Anytime you make changes you just have to repeat Steps 2 to 5.

### Pro Steps:
	cd <path to workspace>
	git clone <link to repo>
	cd Syntel-Website
	git checkout master
	git pull origin master
	// make some changes
	git commit -m "this is what I changed..."
	git push origin master


## Github Desktop
	
	Download/Install Github Desktop: https://desktop.github.com/

### Clone
	1) Click on the green button that is labeled "Clone or Download" and copy the link shown.
	
	2) Start Github Desktop. Go to File > Clone repository > URL tab. 
		Paste the link from Step 1 and set a target directory for the project.

	3) Press "Clone" button.

	NOTE:  This is a one-time setup.  Once you have the repo, you don't need to ever reclone it.

### Workflow
	1) Start Github Desktop. Choose "master" in the "Current Branch" tab's dropdown menu.

	2) Click the "Fetch Origin" button. If the button changes to say "Pull Origin" at any point, press it again.  
		This will update your local repository with the current changes.
		If you have any uncommitted changes, then you'll have to commit them before you can pull (see Steps 4-5).

	3) Make changes to your local project repository (new folders, new files, edit files, delete files, etc.).  
		Any changes you make should appear on the left side in the "Changes" tab.  
		You can also view a history of the project's changes in the "History" tab.

	4) When you have new changes, fill out the "Summary" and "Description" of the changes you've made. 
		Press the "Commit" button on the bottom when you're ready to commit the changes.

	5) Press the "Push origin" button on the top of the application
		This will upload your changes to the remote repository on github. 
		Everyone else on the project should now be able to see and update with your changes.


#    REQUIREMENTS    

	- Python 3.6.X
		* pip (9.0.1)
		* mysql-connector-python (8.0.11)
		* protobuf (3.5.2.post1)
		* setuptools (28.8.0)
		* six (1.11.0)

	- MySQL 8.0.11


#    DATABASE SETUP

	1) Install MySQL and set a username and password for login

	2) Install Python 3.6.X

		NOTE: The following steps will assume Python 3.6.X is your default.
			If not, use 'python3' instead of 'python' when running the Python commands 
			and 'pip3' instead of 'pip' when installing Python requirements.

	3) Navigate to ~/Syntel-Website/ and run the following command in terminal:
		pip install -r requirements.txt

		NOTE: Only 'mysql-connector-python' is listed in requirements.txt, 
		pip will automatically install the other dependencies listed above. 

	4) Run following command in terminal:
		python ~/Syntel-Website/database/database.py <username> <password>

		NOTE: <username> and <password> should be replaced with the login info from Step 1.

	5) Verify that the database and tables were created correctly by launching mysql.
		>mysql -u<username> -p<password>
		mysql> show databases;
		mysql> use restaurant;
		mysql> show tables;

		NOTE: Running each of these commands should yield the following results respectively: 
			- start mysql with login info from Step 1 
			- list all databases (look for 'restaurant') 
			- use 'restaurant' as the current database
			- list the tables contained in 'restaurant'


#    FILE DESCRIPTIONS

## Database
	- database.py
		Python script for building the database schema.

	- database.json
		JSON file containing fake entries to populate database.
	
	- /current/
		Directory holding design documents of our current database structure.
	
	- /old/
		Directory holding first draft of our database structure.

## Images
	Contains all images for the web pages.

## Javascript
	Contains all external javascript files for web pages.

## Stylesheets
	Contains all external CSS files for web pages.

## Requirements
	Text file containing any additional requirements for setup of the environment.

## README
	You're already reading it!

## HTML
	- index.html
		Home page with small sample menu and links to other pages on the website.
	
	- login.html
		Login page for existing users.
	
	- register.html
		Registration/sign-up page for new users. Provides form for user information.
	
	- order.html
		Allows users to add items to their cart and checkout once logged in.
	
	- confirmation.html
		Confirmation page shows successful order by user.
	
	- driver.html
		Employee page for drivers that provide them with info about their current deliveries.
	
	- contact.html
		Company contact page to be viewed by users.
	
	- admin.html
		Employee page for administrators/managers that provide them with info about driver deliveries 
		and allows for editing of those deliveries.		