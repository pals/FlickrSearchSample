Platform Details

- Used Android Studio Version Used 4.1.2
- Android Min SDK 23
- Android Target SDK 30

Highlights

- Flickr Photos open API Demo to search photos and view search list
- View photo details

Libraries & Dependencies

- Kotlin, Ktx libraries, glide, Retrofit, Room database, Coroutines 

Tasks Status 

- Task 1 
	- Search results coming from API 
	- Displayed search box and search button should be at the top screen.
	- Grid view in 2x2 view
	- Each item displays the image and title.
	- The title should be centered under the image.
	- Performed network check before API call and shown toast messages for error. 
	- Orientation change handled

- Task 2
	- Photo details screen
	- The detail view shows the image, title, description, and author - image width and height remained. Details shown below image
	- Image fit XY to take whole space above info section
	- Handled orientation 

- Task 3
	- Task 3 is partially done. 
	- Used Room database to save recent searched keywords
	- Saved searched keyword in database
	- Written queries to delete oldest record

- Task 4
	- Unit test written to test database

- Task 5
	- Not done however checked Accessibility scanner to see what things need to be handled to have accessibility covered in the app. 

Pending tasks, Improvements and optimizations

- Task 2 - Image fills the width of device but in landscape mode image looks pixelated. Displaying width and height 
- Task 3 
	- remaining tasks can be completed by checking local DB count if greater than 6 then delete oldest item and then save new keyword
	- Have horizontal recent search keywords list view, fetch data from DB and show in the list
- Task 4 
	- UI tests
- Task 5
	- Work on accessibility related changes to improve UI and functionality 


