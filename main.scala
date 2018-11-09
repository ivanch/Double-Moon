import scala.math.sqrt
import scala.math.sin
import scala.math.cos
import scala.math.Pi

object main{
    def main(args: Array[String]) = {
        val training_samples = 1000
        val test_samples = 2000

        var train = Array.ofDim[Double](training_samples,2)
        var target = Array.ofDim[Double](training_samples,2)
        var test_data = Array.ofDim[Double](test_samples,2)
        var test_target = Array.ofDim[Double](test_samples,2)

        var d: Double = 0
        var r: Double = 4
        var w: Double = 6

        // Getting position
        for(i: Int <- 0 until training_samples){
            val rand = new scala.util.Random
            var c = rand.nextInt(2)
            if(c == 0){
                var a: Double = rand.nextDouble()*Pi // 0 ~ 180
                var x: Double = sqrt(rand.nextDouble()) * cos(a) * (w/2) + ((if(rand.nextInt(2) == 0) (-r-(w/2)) else (r+(w/2))) * cos(a))
                var y: Double = sqrt(rand.nextDouble()) * sin(a) * w + (r * sin(a)) - d
                train(i)(0) = x
                train(i)(1) = y
                target(i)(0) = 1.0
                target(i)(1) = 0.0
            }else{
                var a: Double = rand.nextDouble()*scala.math.Pi + scala.math.Pi // 180 ~ 360
                var x: Double = (r+w/2) + (sqrt(rand.nextDouble()) * cos(a) * (w/2)) + (if(rand.nextInt(2) == 0) (-r-(w/2)) else (r+(w/2))) * cos(a)
                var y: Double =         - (sqrt(rand.nextDouble()) * sin(a) * (-w)  + (-r * sin(a))) + d
                train(i)(0) = x
                train(i)(1) = y
                target(i)(0) = 0.0
                target(i)(1) = 1.0
            }
        }
        // Getting Test samples
        for(i: Int <- 0 until test_samples){
            val rand = new scala.util.Random
            var c = rand.nextInt(2)
            if(c == 0){
                var a: Double = rand.nextDouble()*Pi // 0 ~ 180
                var x: Double = sqrt(rand.nextDouble()) * cos(a) * (w/2) + (if(rand.nextInt(2) == 0) (-r-(w/2)) else ((r+w/2))*cos(a))
                var y: Double = sqrt(rand.nextDouble()) * sin(a) * w + (r * sin(a)) - d
                test_data(i)(0) = x
                test_data(i)(1) = y
                test_target(i)(0) = 1.0
                test_target(i)(1) = 0.0
            }else{
                var a: Double = rand.nextDouble()*scala.math.Pi + scala.math.Pi // 180 ~ 360
                var x: Double = (r+w/2) + (sqrt(rand.nextDouble()) * cos(a) * (w/2)) + (if(rand.nextInt(2) == 0) (-r-(w/2)) else (r+(w/2))) * cos(a)
                var y: Double =         - (sqrt(rand.nextDouble()) * sin(a) * (-w)  + (-r * sin(a))) + d
                test_data(i)(0) = x
                test_data(i)(1) = y
                test_target(i)(0) = 0.0
                test_target(i)(1) = 1.0
            }
        }

        var nn = new SLP(2,2)
        nn.init()

        for(i: Int <- 0 until training_samples){
            nn.insertInput(train(i))
            nn.feedForward()
            nn.train(target(i))
        }
        println(s"[Train] Train completed on ${training_samples} samples.")

        var ans: Int = 0
        for(i: Int <- 0 until test_samples){
            nn.insertInput(test_data(i))
            nn.feedForward()
            if(nn.out(0) == test_target(i)(0) && nn.out(1) == test_target(i)(1)){
                ans += 1
            }
        }
        println(s"[Right answers]: ${ans}/${test_samples}.")

    }

}

class SLP(inputNodes: Int, outputNodes: Int){
    // Class related
    val ins: Int = inputNodes
    val outs: Int = outputNodes

    // Neuron values
    var input: Array[Double] = new Array[Double](ins)
    var out: Array[Double] = new Array[Double](outs)

    var inOut = Array.ofDim[Double](ins,outs)

    var lr = 0.05

    def init() : Unit = {
        val r = new scala.util.Random

        inOut = inOut.map(_.map(_ => r.nextDouble()))
        println("[SLP] Initialized")
    }

    def insertInput(inputs: Array[Double]): Unit = {
        for(i: Int <- 0 until ins)
            input(i) = inputs(i)
    }

    def feedForward(): Unit ={
        out = out.map(_ => 0.0)
        for(i: Int <- 0 until ins; j: Int <- 0 until outs){
            out(j) += input(i) * inOut(i)(j)
            out(j) = step(out(j))
        }
    }

    def train(target: Array[Double]): Unit ={
        for(o: Int <- 0 until outs; i: Int <- 0 until ins){
            var error: Double = target(o) - out(o)
            inOut(i)(o) = inOut(i)(o) + (error * lr * input(i))
        }
    }

    def step(x: Double): Double = {
        return if(x > 0.5) 1.0 else 0.0
    }

}
