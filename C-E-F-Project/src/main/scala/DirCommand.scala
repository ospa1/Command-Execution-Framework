import scala.sys.process.Process

//prints the current directory
class DirCommand extends Command {
  private val com: String = "Dir"
  private var opt: String = ""

  //optional arguements
  // not implemented in build function yet
  def setOptions(opt: String): DirCommand.this.type = {
    this.opt = opt
    return this
  }

  //checks for options and executes a process for the command
  //returns a sequence of type string  -- Seq[String]
  def build(): Seq[String] = {

    val comm = Seq("powershell",this.com)
    val result = Process(comm).!!
    return Seq(result)
  }
}
