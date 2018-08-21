import scala.math.sqrt
import scala.math.sin
import scala.math.cos
import scala.math.Pi

object main{
    def main(args: Array[String]) = {
        val training_samples = 20000
        val test_samples = 10000

        var train = Array.ofDim[Double](training_samples,2)
        var target = Array.ofDim[Double](training_samples,2)
        var test_data = Array.ofDim[Double](test_samples,2)
        var test_target = Array.ofDim[Double](test_samples,2)

        // Getting position
        for(i: Int <- 0 until training_samples){
            val r = new scala.util.Random
            var c = r.nextInt(2)
            if(c == 0){
                var a: Double = r.nextDouble()*Pi // 0 ~ 180
                var x: Double = sqrt(r.nextDouble()) * cos(a) * 3 + (if(r.nextInt(2) == 0) (-7) else (7)*cos(a))
                var y: Double = sqrt(r.nextDouble()) * sin(a) * 6 + (4 * sin(a))
                train(i)(0) = x;train(i)(1) = y
                target(i)(0) = 1.0;target(i)(1) = 0.0
            }else{
                var a: Double = r.nextDouble()*scala.math.Pi + scala.math.Pi // 180 ~ 360
                var x: Double = 7 + (sqrt(r.nextDouble()) * cos(a)*3 + (if(r.nextInt(2) == 0) (-7) else (7))*cos(a))
                var y: Double = -(sqrt(r.nextDouble()) * sin(a) * (-6) + (-4 * sin(a)))
                train(i)(0) = x;train(i)(1) = y
                target(i)(0) = 0.0;target(i)(1) = 1.0
            }
        }
        // Getting Test samples
        for(i: Int <- 0 until test_samples){
            val r = new scala.util.Random
            var c = r.nextInt(2)
            if(c == 0){
                var a: Double = r.nextDouble()*Pi // 0 ~ 180
                var x: Double = sqrt(r.nextDouble()) * cos(a) * 3 + (if(r.nextInt(2) == 0) (-7) else (7)*cos(a))
                var y: Double = sqrt(r.nextDouble()) * sin(a) * 6 + (4 * sin(a))
                test_data(i)(0) = x;test_data(i)(1) = y
                test_target(i)(0) = 1.0;test_target(i)(1) = 0.0
            }else{
                var a: Double = r.nextDouble()*scala.math.Pi + scala.math.Pi // 180 ~ 360
                var x: Double = 7 + (sqrt(r.nextDouble()) * cos(a)*3 + (if(r.nextInt(2) == 0) (-7) else (7))*cos(a))
                var y: Double = -(sqrt(r.nextDouble()) * sin(a) * (-6) + (-4 * sin(a)))
                test_data(i)(0) = x;test_data(i)(1) = y
                test_target(i)(0) = 0.0;test_target(i)(1) = 1.0
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

        var ans: Int = 0;
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

    var InOut = Array.ofDim[Double](ins,outs)
    //var OutBias = new Array[Double](outs)

    var Learning_Rate = 0.05


    def init() : Unit = {
        val r = new scala.util.Random

        InOut = InOut.map(_.map(_ => r.nextDouble()))
        //OutBias = OutBias.map(_ => r.nextDouble())
        println("[SLP] Initialized")
    }

    def insertInput(inputs: Array[Double]): Unit = {
        for(i: Int <- 0 until ins)
            input(i) = inputs(i)
    }

    def feedForward(): Unit ={
        out = out.map(_ => 0.0)
        for(i: Int <- 0 until ins; j: Int <- 0 until outs)
            out(j) += input(i) * InOut(i)(j)// + OutBias(j)

        out = stepFunction(out)
    }

    def train(target: Array[Double]): Unit ={
        for(o: Int <- 0 until outs; i: Int <- 0 until ins){
            var error: Double = target(o) - out(o)
            InOut(i)(o) = InOut(i)(o) + (error * Learning_Rate * input(i))
        }
    }

    def stepFunction(arr: Array[Double]) = {
        arr.map{x => if(x > 0.5) 1.0 else 0.0}
    }

}
