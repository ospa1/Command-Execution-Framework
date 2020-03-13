trait Command {

  //corresponding command
  private val com:String = ""

  //build should execute process and return a Seq(String) to use for combinators
  def build(): Seq[String]
}
