import scala.sys.process.Process

//this command returns the current date in the system
class DateCommand extends Command {
  private val com: String = "Get-Date"

  // builds the command and processes it returning a Sequence of type string
  def build(): Seq[String] = {
    val x =Seq("powershell", this.com)
    val result = Process(x).!!
    return Seq(result)
  }
}
