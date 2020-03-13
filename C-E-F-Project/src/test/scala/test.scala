import org.scalatest._

import scala.sys.process.Process

class test extends FlatSpec with Matchers {

  //makes sure there is a result
  def f(string: String): String = {

    if (string.length > 0){
      return "success"
    }
    else{
      return "failed"
    }
  }

  "dir command" should "return success" in{

    val dirobj = (new DirCommand).build()
    val res = dirobj.map(_.toUpperCase)
    val test = res.map(x => f(x))
    assert(test.contains("success"))
    assert(dirobj.map(a=>a) == dirobj)//from class
  }
  "md command" should "return success" in{

    //creates a folder then erases it
    val mdobj = (new MdCommand).setFolder("testFolder").build()
    val res = mdobj.map(_.toUpperCase)
    val test = res.map(x => f(x))
    assert(test.contains("success"))
    assert(mdobj.map(a=>a) == mdobj)
    Process("""powershell remove-item testFolder""").!!
  }

  "Date command" should "return success" in{

    val obj = (new DateCommand).build()
    val res = obj.map(_.toUpperCase)
    val test = res.map(x => f(x))
    assert(test.contains("success"))
    assert(obj.map(a=>a) == obj)
  }

  "Process command" should "return success" in{

    val obj = (new ProcessCommand).build()
    val res = obj.map(_.toUpperCase)
    val test = res.map(x => f(x))
    assert(test.contains("success"))
    assert(obj.map(a=>a) == obj)
  }

  "Curl command" should "contain 200 OK" in{

    val obj = (new CurlCommand).setWebsite("yahoo.com").build()
    assert(obj.map(x => x.contains("200 OK")).contains(true))
    assert(obj.map(a=>a) == obj)
  }

  "various commands" should "use date to create folder and check for it" in{
    val date = (new DateCommand).build()
    //create a folder named false
    val fold1 = date.map(x => (new MdCommand).setFolder(x.isEmpty.toString).build())
    //check its there
    val dir = (new DirCommand).build()
    val result = dir.map(x => x.contains("false"))
    assert(result.contains(true))
    Process("""powershell remove-item false""").!! // delete folder afterwards
  }

  "dir and date commands" should "use date to replace dir command results" in{
    val dir = (new DirCommand).build()
    val date = dir.map(x => (new DateCommand).build())
    assert(date.length > 0)
  }

}
