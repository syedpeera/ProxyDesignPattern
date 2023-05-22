# ProxyDesignPattern

Overview:
Proxy Design Pattern is a type of structural design pattern which is used whenever we need a placeholder or representational object that can work in place of the real object. The proxy acts as an intermediatory layer between the client and the real object and hence can control the access to the real object, add additional functionality, and even restrict client access. It is also known as Surrogate Pattern.

When Will We Need Proxy Design Pattern?
While dealing with software applications in our day-to-day life, it is not always possible to load or display heavy images and video files especially while dealing with limited resource devices or low bandwidth internet connectivity. To ensure uninterrupted service in these cases, software developers make use of the Proxy Design Pattern. Let us try to understand how all of this is done through an example.

Let's say we have been asked to design an Instagram-like application that loads and displays images on a limited-resource smartphone device. Considering the functionality i.e., to load and display the image, we decided to create an Image interface that defines some general operations we need to perform such as determining whether the Image should have borders or not, rendering and displaying the image on the screen, etc.

Here, the class Image Details represents the details of the image i.e. the location, name, and other properties of the image. The memory-intensive operations in the application include the loading of the image from the system and the display of the image to the client. Loading and display of image files is a tedious task for limited-resource devices. Let's try to develop this application using the brute force approach.

To implement the above-described application, we require an Image interface that describes the desired functionality of the application. It should define the desired functionality and should be in direct contact with the client. The Image interface in Java programming language looks something like this:

Image Interface:
// Image Interface - Defines the operations used by the Client
public interface Image {
    
    // Image Border Functionality
    boolean getBorder();
    void setBorder(boolean border);

    // Image Location Functionality
    double[] getLocation();
    void setLocation(double x, double y);

    // Image Display Functionality
    void display();
}

As discussed earlier that the Image Interface defines the desired functionality of the application. Hence, to provide the actual implementation and meet the desired functionality, we need to implement this Image Interface. This can be achieved by constructing a RealImage class that implements the functionality by overriding the methods defined in the interface. It includes: getLocation(), setLocation(), setBorder(), getBorder(), and the display() method.

Here, the setters and getters for the Location() method are used to set and retrieve the location of the centroid of the 2D image, the setBorder() and getBorder() method provides the border information of the image, and the Display() method provides the display functionality in the application.

RealImage Class (implements the Image interface):

// RealImage - Concrete class that provides actual functionality
public class RealImage implements Image {

    // Instance variables - To store image-related data
    private double[] location = new double[2];
    private String name;
    private boolean border;

    // Constructor - Used for object initializations
    public RealImage(String filename) {
        // Loads image from disk
        System.out.println("Loaded from disk : " + filename);
        name = filename;
    }

    // Image Border Information
    @Override
    public void setBorder(boolean border) {
        // sets the image border value
        this.border = border;
    }

    @Override
    public boolean getBorder() {
        // returns the image border value
        return this.border;
    }
    
    // Image Location Information
    @Override
    public void setLocation(double x, double y){
        // sets the image location
        this.location[0] = x;
        this.location[1] = y;
    }

    @Override
    public double[] getLocation() {
        // returns the image location
        return this.location;
    }
    
    // Image Display Functionality
    @Override
    public void display() {
        // Display the Image
        System.out.println("Displayed : " + this.name);
    }
}

Here, the RealImage class represents the blueprint of the actual image object that we need to display to the client using our application. The above program loads the specified image from the disk whenever a RealImage class' object is initialized.

The client is the driver code that makes use of the services provided by the application. It requires the application to provide image-related functionalities irrespective of how they are implemented below the hood. The main objective of the application is to fulfill the requirements of the client i.e., to provide an efficient way of displaying and loading images from the System’s hard disk.

Now, to provide this functionality to the Client, we can create a Client class that can be used by the users of the application. The Client class can be set up like this:

Client:
// Client - Take use of the Service.
public class Client {
    public static void main(String[] args) {
        // Creating a new RealImage object
        Image img = new RealImage("Pic.jpg");

        // Setting Location of the Image
        img.setLocation(1.0, 0.0);
        double[] loc = img.getLocation();

        // Setting the Image Border to True
        img.setBorder(true);

        // Getting Details related to the Image.
        System.out.println("\n-- Image Details --");
        System.out.println("Image Border : " + img.getBorder());
        System.out.println("Image location : (" + loc[0] + "," + loc[1] + ")");

        // Displaying the Image
        System.out.println("\n\n-- Displaying image now --");
        img.display();
    }
}

Here, the Client is creating a new RealImage object by passing the filename Pic.jpg. The output retrieved by the Client is :

Client's Output:

Loaded from disk : Pic.jpg

-- Image Details --
Image Border : true
Image location : (1.0,0.0)


-- Displaying image now --
Displayed : Pic.jpg

Notice that every time the client creates a new RealImage object, the Image file gets loaded from the System’s hard disk. The loading of image file from the disk is a resource-intensive operation i.e., it requires a lot of system resources.

Drawbacks in Application Design (Need of Proxy Design Pattern):

Now, consider a case in which the client only wants to access the details related to the Image i.e., whether the image has a border or not and the location of the image's center. Even to access these details, the client will have to load the file from the disk during the creation of the RealImage object.

This is a major drawback in our design as we were asked to build this application for resource-bound devices. Hence, we need a way to hold the creation of the heavy RealImage object until it is absolutely necessary i.e., we need a way to load the file from the disk only when the client wishes to use the display functionality. All other image details should be accessible without using a vast majority of system resources. This is where we can use the Proxy Design Pattern.

How Does Proxy Design Pattern Work?
A proxy in the real world is someone who can act as a substitute for another person, whether in corporate work, in-person attendance, etc. The main characteristic of a proxy is its similarity with the original object. This feature makes the proxy indistinguishable and allows it to act as a substitute for the real object.

The Proxy Design Pattern allows us to create a proxy class whose objects can be used in place of original objects in an application. The proxy acts as an intermediatory layer between the client and the real object and hence can control the access to the real object. The proxy class also allows us to add extra functionality by performing something either before or after the client request gets through to the original object.

By using the proxy design pattern we can provide representational objects (proxies) to the client instead of real objects. Due to the similarity between the proxy and the real object, the client can be completely unaware of the proxy present between the client and the real object. This similarity between the proxy and the real object is achieved by implementing the same interface in the proxy as that used by the real object.


Based on the functionality the proxy provides, there can be three different kinds of proxies that we can use in an application:

1. Remote Proxy: These are used to provide a local representation of a remote object (the object that belongs to a different address space). Here we create a local copy of an object that lies on the server for faster loading.

2. Virtual Proxy: These are used to delay the construction/loading of the actual object until absolutely necessary. A virtual proxy is a placeholder for heavy or expensive-to-create objects. This is the proxy that was elaborated in the above example of the Image application.

3. Protection Proxy: Protection proxies are bodyguard proxies that control access to the original object's methods. It acts as an authentication system that allows only authentic client requests to pass through to the actual object.

Let's look at the structure of the Proxy Design Pattern and understand how it can be used to solve the earlier-discussed issue.

Structure:
The structure of the Proxy Design Pattern is shown below:


Here, we can notice that the Proxy Design Pattern uses the following terms:

Subject (Interface)

1. It defines the interface which is used by the client.
2. It is the common interface for RealSubject and Proxy so that a Proxy can be used anywhere a RealSubject is expected.
3. It defines the operations that are to be expected by the actual implementations.

RealSubject (Normal Class)

1. It implements the Subject and provides the real implementation of the operations defined in the Subject Interface. It provides the actual functionality in the application by overriding the operations defined by the Subject Interface.
2. It provides some useful business logic.

Proxy (Normal Class)

1. It implements the Subject Interface to disguise itself as a Real Subject's object.
2. It also maintains a reference to the RealSubject to provide actual functionality.
3. It controls access to the RealSubject and may be responsible for its creation and deletion.

Example
Now, let's discuss how the Proxy Design Pattern can be used to solve the earlier discussed problem.

In our application, we require a way to load the file from the disk and initialize the RealImage object only when the client wishes to use the display functionality. Here, we can add a virtual proxy object that can control the initialization of the resource-intensive RealImage object until it is absolutely necessary.



Here, we can notice that the Client uses the Image Interface for the application's functionality. Now, instead of providing the actual object to the Client, we are passing a Virtual Proxy object. The client won't be able to differentiate between proxy and actual object as they both implement the same Image interface.

Whenever a RealImage object is created, the image is loaded from the disk. This is a resource-intensive operation. Using a virtual proxy allows us to delay the creation of the RealImage object unless and until the Client wishes to display the image on the screen. In that case, we have to load the image and display it. In all other cases, the Proxy object can represent the actual RealImage object and the application will behave normally and efficiently.

Implementation and Pseudocode:
After identifying the use of the Proxy Design Pattern in our application, we can implement it in the following steps:

Step 1: Create a Proxy class that implements the same interface as that of the Real object.
Step 2: Update the application in such a way that it passes proxy objects in place of original objects.
Step 3: Prepare a condition according to which the Client should be able to use the Real object.

The above 3 steps can be implemented for the earlier-discussed issue in the following manner:

Step 1: Creating the Proxy class

// Proxy - Virtual Proxy class
public class Proxy implements Image {

    // Reference to the RealImage object.
    private RealImage image;

    // Instance variables
    private double[] location = new double[2];
    private String name;
    private boolean border;

    // Constructor
    public Proxy(String filename) {
        // Instead of Loading the file, just cache the content.
        this.name = filename;
    }

    @Override
    public void setBorder(boolean border) {
        if(image != null){
            image.setBorder(border);
        } else{
            this.border = border;
        }
    }

    @Override
    public boolean getBorder() {
        if(image != null){
            return image.getBorder();
        }
        return this.border;
    }

    @Override
    public void setLocation(double x, double y) {
        if(image != null){
            image.setLocation(x, y);
        } else{
            this.location[0] = x;
            this.location[1] = y;
        }
    }

    @Override
    public double[] getLocation() {
        if(image != null){
            return image.getLocation();
        }
        return this.location;
    }

    @Override
    public void display() {
        // Create a RealImage object if it doesn't exist.
        if(image == null){
            image = new RealImage(name);
            if(border){
                image.setBorder(border);
            }
            if(location != null){
                image.setLocation(location[0], location[1]);
            }
        }
        // Call the Display Functionality.
        image.display();
    }
}

Here, we can notice that the Proxy class is similar to the RealImage class except for the fact that:

It has a reference to the RealImage object.

It checks whether the RealImage object is already present or not in every method. If the RealImage object is not there, the Proxy class caches (stores) all the data for future use. It allows us to delay the initialization of the heavy RealImage object until it's absolutely necessary.

It creates the RealImage object only when the display() method is called, thereby saving the device resources.

Step 2: Updating the application

// Client - Take use of the Service.
public class Client {
    public static void main(String[] args) {
        // Passing the Proxy in place of RealImage object.
        Image img = new Proxy("Pic.jpg");

        // Setting Location of the Image
        img.setLocation(1.0, 0.0);
        double[] loc = img.getLocation();

        // Setting the Image Border to True
        img.setBorder(true);

        // Getting Details related to the Image.
        System.out.println("\n-- Image Details --");
        System.out.println("Image Border : " + img.getBorder());
        System.out.println("Image location : (" + loc[0] + "," + loc[1] + ")");

        // Displaying the Image - Creating the RealImage object
        System.out.println("\n\n-- Displaying image now --");
        img.display();
    }
}

Here, to update our application we only had to replace the RealImage object creation statement in the Client. In this way, the Proxy object is used by the Client and the RealImage object is only created when the Client is calling the display() method. This can be shown by noticing the output retrieved by the Client:

Client's Output:

-- Image Details --
Image Border : true
Image location : (1.0,0.0)


-- Displaying image now --

Loaded from disk : Pic.jpg
Displayed : Pic.jpg

We can notice that the Client is now able to access the details of the Image without actually loading the image from the disk. The Image is only loaded from the disk when the Client requests the display functionality. Hence, the Proxy Design pattern is delaying the initialization of the actual object, saving a ton of resources. This makes our application efficient, faster, and more reliable.

Pros and Cons of Proxy Design Pattern:

Let's look at some advantages of the Proxy Design Pattern:

1. Security: Proxy provides an additional layer of protection to the original object from the outside world by adding an intermediatory layer between the client and the actual implementations.
2. Better Performance: By avoiding the creation or duplication of memory-intensive objects and caching frequently accessed objects, proxies can improve the performance of the application.
3. Reliability: Since the proxy is similar to the actual object and contains the essential information related to the object, the proxy can work in place of actual objects even if the actual service is not ready or is not available.

Now, let's understand some of the disadvantages of the Proxy Design Pattern:

1. Complexity: We have to write repeated code as the proxy is similar to the actual object. Hence, it increases the code complexity.
2. Extra Effort: To update the application, the developer must concurrently update the proxy object alongside the real object.
3. Ambiguity: Proxies are meant to act as a substitute for real and heavy objects. If somehow, some clients are able to directly access the real objects instead of proxies, then the application may exhibit disparate behavior.

Difference between Proxy Design Pattern And Other Patterns:
1. Adapter Pattern: Adapter pattern is a structural design pattern that implements a different interface to allow objects with incompatible interfaces to collaborate, whereas the Proxy pattern implements the same interface as its subject.
2. Decorator Pattern: It is a structural design pattern that acts as a wrapper that adds additional functionality and responsibilities to objects, whereas the Proxy pattern only controls the access to the object by acting as an intermediatory layer between the client and the implementation.
3. Facade Pattern: It is also a structural design pattern that provides a simplified interface to any complex class. The facade pattern defines a new interface for existing objects, whereas the Proxy pattern makes use of the existing subject's interface.

FAQs:
Q1: When should we use the Proxy Design Pattern ? Proxy Design Pattern is used whenever we want:

Lazy initialization: Process of delaying the creation of resource-intensive object until it's absolutely necessary. (Virtual Proxy)

Local Representation: Whenever we need a local representation of a remote object i.e., the object present in a different address space. (Remote Proxy)

Logging and Caching: Whenever we need to add a level of security by logging the requests and providing controlled access to the object. (Protection Proxy)

Q2: Describe a Real word example of a Proxy Design Pattern?
Credit cards can be considered as a proxy for cash. Credit cards can be used in place of cash and provide extra functionalities in form of reward points.

Q3: What is the purpose of Proxy?
The purpose of a proxy is to simplify and control the complexity of the client's requests by providing additional benefits such as privacy and security. It creates a wrapper to cover the main object's complexity from the client by adding an intermediatory layer between the client and the actual implementations.
