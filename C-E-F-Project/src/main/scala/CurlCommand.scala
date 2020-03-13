import scala.sys.process.Process

// curl command returns the webpage results using curl
class CurlCommand extends Command {
  private val com: String = "curl"
  private var website = ""

  //set the website
  def setWebsite(site: String): this.type ={
    website = site
    return this
  }

  // checks if a website is entered and returns the result of the execution
  // if no website is entered or there is an exception of any type it returns an empty string in a sequence
  // the string is the web page curl results
  override def build(): Seq[String] = {

    try{
      //check for options
      if (website.length > 0){
        val s = Seq("powershell", com, website)
        val result = Process(s).!!
        return Seq(result)

      }
      //no website entered
      else{
        return Seq("")
      }
    }
      //if an error occurs return an empty sequence
    catch {
      case e:Exception =>{
        return Seq("")
      }
    }
  }
}
