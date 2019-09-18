# TemplateIT
> Project Orginization Tool

__TemplateIT__ makes it easy to keep projects clean and orginized with project templates. It allows users to create a template project folder that can be exported to a directory. This program can be used to make video editing organized with templated folders for all of the users resources. 


## Main Classes
* #### __Folder__
>  * Keeps track of it sub folders using ArrayList
>  * Keeps track of its parent folder
>  * Has an object of its JButton which is accessed from the template

* #### __Writer__
>  * Uses FileOutputStream to write the root folders data into a file
>  * This allows for all of the sub folders to be stored into a template file

* #### __Reader__
>  * Uses FileInputStream to read the template file and create the root folder
>  * Easy template reading

* #### __Output__
>  * Uses recursion to sort through all of the sub folders and output them to a directory

* #### __Window__
>  * Creates GUI using Java Swing
>  * Holds all of the functions for creating new Folders 
>  * Displays all sub-folders of root folder
