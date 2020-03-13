import scala.sys.process.Process

//this command returns the active processes in the system
class ProcessCommand extends Command {
  private val com: String = "Get-Process"

  // builds the process command and executes it
  // returns the result in a sequence of type string
  def build(): Seq[String] = {
    val x =Seq("powershell", this.com)
    val result = Process(x).!!
    return Seq(result)
  }

}
