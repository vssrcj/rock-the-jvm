package lectures.part0basics

object DefaultArgs extends App {
  def savePicture(format: String = "jpg", width: Int = 1920, height: Int = 1080) = println("save")

  savePicture(height = 800, format = "image")
}
