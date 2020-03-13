import scala.sys.process.Process

//this command creates a folder
class MdCommand extends Command {
  private val com: String = "md"
  private var folder: String = ""

  // sets the name of the folder
  def setFolder(f:String): this.type ={

    //checks if spaces are used and uses the first word
    val first = f.split(" ")
    folder = first(0)
    return this
  }

  //checks for a valid folder name and executes the command
  override def build(): Seq[String] = {

    //checks that a name is set
    if (folder.length > 0){
      val comm = Seq("powershell",this.com, folder)

      //tries to execute the process sends back the result
      try{
        val result = Process(comm).!!
        return Seq(result)
      }
      catch{
        case x:Exception =>{
          return Seq("")
        }
      }
    }
      // if there is no folder name
    else{
      return Seq("")
    }
  }
}
