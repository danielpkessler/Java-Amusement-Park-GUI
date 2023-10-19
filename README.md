# Java-Amusement-Park-GUI

Amusement Park Ride Database

The provided Java code defines a class named Bdd that serves as a database for storing and managing information about amusement park rides. The Bdd class uses two maps: one to store a list of rides associated with each park, and another to link each ride with a TreeSet of parks where it can be found. It includes methods for adding rides to parks, retrieving the list of rides in a specific park, finding parks where a particular ride is located, and calculating the frequency of each ride's appearance. The code demonstrates fundamental Java concepts such as data structures, object storage, and manipulation, encapsulation, and map-based data organization.


Window Event Handling in Java Swing Application

The provided Java code defines a class named Fenetre used for handling window events in a Swing GUI application. This class inherits from WindowAdapter, which is a class that receives window events. The code includes a constructor that takes a GUI object and a method windowClosing that is overridden to handle the window closing event. When the window is closed, the associated GUI becomes visible again. This code showcases a fundamental Java concept of event handling and graphical user interface (GUI) programming using the Swing library.


Swing GUI Application for Displaying Amusement Park Data

This Java code defines a Swing GUI application that allows users to interact with and display information about amusement parks and their rides. The code creates a main GUI window with options to select amusement park rides and parks using JComboBox. It also includes a menu with options to display ride frequency and exit the application. The application reads data from a file to initialize an object of the Bdd class, which stores and manages the data. Users can select specific rides and parks, and when they do, additional windows (ManegeInfo and ParcInfo) are created to display more information. The code demonstrates Java's GUI programming using the Swing library, event handling, and the organization of a multi-window application.


Data Reading and GUI Initialization

The provided Java code demonstrates the process of reading data from a file, populating JComboBox components in a graphical user interface (GUI), and creating event listeners to interact with the data. The code starts by creating JComboBox components for selecting amusement park rides and parks. It reads data from a file, initializing an object of the "Bdd" class, which acts as a database for this information. The code sets up an event-driven GUI using Swing, allowing users to select rides and parks. It also handles the actions when users select a ride or park, opening new interfaces (ManegeInfo and ParcInfo) to display information about the selected ride or park. This code showcases data reading, GUI design, and event handling in a Java application.


Amusement Ride Data Structure

The provided Java code defines a class named "Manege" that represents amusement park rides. The "Manege" class is designed to hold information about rides, including their name, height, and speed. It provides constructors to create ride objects based on their name and additional attributes. This class implements the "Comparable" interface to allow comparison of rides based on their names, ensuring they can be sorted. Additionally, it overrides the "hashCode" and "equals" methods to handle object equality based on the ride's name, making it useful for data storage and retrieval. The "toString" method provides a string representation of the ride, including its name, height, and speed. This code defines the fundamental data structure for managing amusement rides in a Java application.


Amusement Ride Information Interface

This Java code defines a class named "ManegeInfo," which serves as an information interface for amusement park rides. The "ManegeInfo" class extends the "JFrame" class, creating a window to display detailed information about a specific ride. The provided information includes the ride's name, height, and speed. The class constructs a user interface that shows the name of the ride as the window's title and presents additional information as a JLabel at the top. A list of parks where the ride is located is displayed below.
The user interface allows for scrolling when the window size is insufficient to display all park names. A "WindowListener" is added to the window, which allows users to close the ride information window and return to the main application window (the "GUI" interface). The code contributes to a user-friendly and informative application for managing amusement ride data.


Amusement Park Information Interface

The Java code defines a class named "ParcInfo," which serves as an information interface for amusement parks. This class extends the "JFrame" class, creating a window to display detailed information about the rides within a specific park. The provided information includes the name, speed, and height of each ride in the park.
The user interface allows navigation between the rides in the park using "Previous" and "Next" buttons, along with an indicator of the current ride's position. The code contributes to a user-friendly application for exploring ride data within an amusement park. The "ParcInfo" class is used in conjunction with the "GUI" interface for a comprehensive amusement park management system.
