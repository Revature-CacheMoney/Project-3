Frontend Readme - updated 2/28/22

Getting started:
- Create a branch off of "frontend" -
		- git checkout frontend
		- git checkout -b your_new_branchname
		- Navigate to the root directory of frontend (where the package.json is located)
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
- Please place all css files in ./css/.  You can import them into the component with: import "../css/yourfile.css";
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




