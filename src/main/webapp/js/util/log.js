/**
 * Created by syy on 2017/1/9.
 */
function log() {
    try {
        console.log.apply(console,arguments)
    }catch (e){
        try {
            opera.postError.apply(opera, arguments)
        } catch (e) {
            alert(Array.prototype.join.call(arguments," "))
        }
    }
}