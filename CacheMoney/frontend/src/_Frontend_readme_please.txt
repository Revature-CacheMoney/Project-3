Frontend Readme - updated 3/18/22

Known issues:
- Account list not updating - see AccountList.js
- Horizontal scrollbar displayed on MainPageView - something is causing it to overflow, so
	we forcibly hid the bar without solving real problem
- No error feedback on transactions - ex. if you try to withdraw too much from an account,
	it is silently rejected with no user feedback - may require backend response changes as well
- No error feedback on registrations - ex. if the username/email address is already exists,
	there is no feedback given to the user on what the actual response - may require backend response changes
- Chatbox styling on live/hosted version of the site not being overridden by !important tags
	- May just want to create your own Chatbot component with similar functionality since the library 
		used is difficult/impossible to override and uses a lot of fixed styling
- CSS changes not being reflected on live site - Not sure, annoy your devops person.  I _think_ we 
determined this had to do with CloudFront? and delays on AWS's side but not sure if workarounds
(invalidating the cache) fully worked
- Background images not showing on live side - See Cody's cursed solution - probably something to do
with webpack and non-development React builds
- CSS cleanup - we tried


Getting started:
- Create a branch off of "frontend" -
		- git checkout frontend
		- git checkout -b your_new_branchname
		- Navigate to the root directory of frontend (where the package.json is located) 
				-- ex. Project-3/CacheMoney/frontend/
		- npm install
		- npm start (to run the app)
- Do your thing!  Adopt a component, make one, or fiddle with CSS, or make some improvements.
- When done, make a merge request to merge with the "frontend" branch
- Requests will have to be approved before the merge completes - just an fyi!


Flowchart / Plans: 
See Front_End_Design.pdf (located in the root directory of the project repository, or maybe in /frontend/planning)


Mockups:
See images in ./fronend/planning/


Guidelines:
- Please place all components in ./components/
- Please place all your css in css/App.css.  (This is subject to change... again)
- Feel free to add new components to fit your needs.  Some have been made just to help flesh out the structure/planning.
- Currently using multiple css files for component-specific styling BUT this may change in the future
(combining into less files or maybe using SASS - TBD).  Just be careful with adding in styling that affects everything
(ex. body tag) since there may be unexpected consequences / conflicts.  Maybe put these kind of cross-component styling
into a single css file / App.css?
- Try to follow the flowchart / mockups as best as possible.  If you can't quite get things work as you want them to,
feel free to ask for help and/or add a note/comments if things need to be improved.


Colors Used: 
Gold: #d3a940;
MoneyGreen: #3d703a;	// Deeper green used ex. on splash screen / login menu
Dark Green: #3e4f3d;  	// Used for the shadows under navbar
Light Green: #6c896a;
"Black": #4c4c4b;




