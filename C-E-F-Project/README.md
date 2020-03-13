Oscar Palomino

Command execution framework

Design:

    this design uses the builder pattern to make and execute powershell commmands.
    when invoking the build function, all commands return a sequence of type string with the result in it.
    this was done to be able to use the result with combinators. all the commands inherit
    from a Command super class to keep basic things in uniform this allows commands to later
    be refined by adding more functionality if needed.
    
    this project uses scala version 2.13.0 with sbt
    the commands were tested on windows 10
 
    commands:
        DirCommand - displays the directories
                   - example (new DirCommand).build()
        
        CurlCommand- uses a website name to retrieve its contents
                   - it needs a valid website set using the setWebsite function
                   - example: (new CurlCommand).setWebsite("yahoo.com").build()
                   - this one takes a little longer to execute and sometimes a little box pops up asking to allow cookies to be stored
                      but it goes away after the command finishes
                      
        ProcessCommand- displays all the current processes in a system
                      - example: (new ProcessCommand).build()
        
        MdCommand- creates a folder given a name
                 - it neds a valid forlder name set using the setFolder function
                 - example: (new MdCommand).setFolder("hello").build()
        
        dateCommand- displays the system date
                   - example: (new DateCommand).build()
    
    pros and cons:
        pros -  commands can be used with monadic combinators.
                it is simple to add more commands and options
                it hides information
                purely functional except in 2 cases when an input need to be stored, like .setFolder("folder"), .setWebsite("yahoo.com"), then a mutatable variable is used
                
        cons -  some of the commands that rely on user input can break a command if the input tries steer away from the commands intention
                i was not sure if having a super class for commands was the way to go maybe a different approach was better
         
                
    results:
        DirCommand - (new DirCommand).build()
        
        List(
        
            Directory: C:\Users\OP\Documents\oscar_palomino_hw3\Oscar_Palomino_hw3
        
        
        Mode                LastWriteTime         Length Name                                                                  
        ----                -------------         ------ ----                                                                  
        d-----       11/27/2019   5:05 PM                .idea                                                                 
        d-----       11/27/2019   4:54 PM                hello                                                                 
        d-----       11/27/2019   2:45 PM                lib                                                                   
        d-----       11/27/2019   2:45 PM                project                                                               
        d-----       11/27/2019   2:45 PM                src                                                                   
        d-----       11/27/2019   2:45 PM                target                                                                
        -a----       11/17/2019   2:57 PM            219 build.sbt                                                             
        -a----       11/27/2019   5:05 PM           3996 README.md                                                             
        
        
        )


        CurlCommand- val obj = (new CurlCommand).setWebsite("yahoo.com").build()
                     val res = obj.map(x => x.contains("200 OK")).contains(true)
                     
        the first line will produce a sequnce of type string that has the information for
        the yahoo website. a box might pop up on the screen about cookies but it goes away 
        result looks like this:
        
        List(
        
        StatusCode        : 200
        StatusDescription : OK
        Content           : <!DOCTYPE html>
                            <html id="atomic" lang="en-US" class="atomic my3columns  l-out Pos-r https fp fp-v2 rc1 fp-default 
        ...
        )
        
        the second line will map the first item on the list and check if 200 OK is contained
        then it stores the result in a list which it checks for evaluating to true which is stored in res variable
        
        
        ProcessCommand- (new ProcessCommand).build()
    
        List(
        Handles  NPM(K)    PM(K)      WS(K)     CPU(s)     Id  SI ProcessName                                                  
        -------  ------    -----      -----     ------     --  -- -----------                                                  
            162      10     1476       2664              3164   0 AdobeUpdateService                                           
            216      13     2580       3992              3136   0 AGMService                                                   
            349      16     4272      11956              3244   0 AGSService                                                   
            164      12     2376       1756       0.20   6332   2 amdow
            ...
         )
        
        
        MdCommand- (new MdCommand).setFolder("hello").build()
        
        List(
        Mode                LastWriteTime         Length Name                                                                  
        ----                -------------         ------ ----                                                                  
        d-----       11/27/2019   3:33 PM                hello  
        )
        
        dateCommand- (new DateCommand).build()
        
        List(
        Wednesday, November 27, 2019 4:50:25 PM
        )
         
                    
How to install:
    
    import folder into intellij
    
How to run:
            
    on sbt shell within intellij:
       test

    

    
