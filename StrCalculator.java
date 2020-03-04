public class StrCalculator {
    String argstr1;
    Character operand;
    String argstr2;
    char[] result;
    static final int RESULTMAXSIZE = 40;
    String err;

    public StrCalculator(String s){
        if(!this.formulaByElement(s)){
            argstr2=argstr1="";
        }
    }

    private void setOperand(char ch){
        if("+-/*".indexOf(ch)>=0){
            operand=ch;
        }
    }

    private boolean  formulaByElement (String s) {
        String s1 = s.trim();
        if(s1.charAt(0)!='"' || s1.matches("(.*)[\\+\\-\\/\\*](.*)")==false){
            err = "Input string \""+s+"\"has wrong format";
            throw new IllegalArgumentException(err);
        }
        String argstr[]=new String[2];
        argstr = s1.split("[\\+\\-\\/\\*]",2);
        if (argstr[0]!=null) {
            setOperand(s1.charAt(argstr[0].length()));
        } else {
            err = "First Argument is empty";
            throw new IllegalArgumentException(err);
        }
        argstr1=argstr[0].trim().substring(1,argstr[0].trim().indexOf('"',1));
        argstr2=argstr[1].trim();
       if ((operand == '*' || operand == '/') && argstr2.charAt(0) !='"'){
            if (!argstr2.matches("[0-9]+")|| Integer.valueOf(argstr2)>10 ||Integer.valueOf(argstr2)<1 || argstr1.length()>10){
                err = "Second Argument \""+argstr2+"\"has wrong format";
                throw new IllegalArgumentException(err);
            }
        } else if ((operand =='+' || operand =='-') && argstr2.charAt(0)  =='"'){
            argstr2=argstr2.substring(1,argstr2.indexOf('"',1));
            if (argstr2.length()>10 || argstr1.length()>10 ) {
                err = "Second Argument \""+argstr2+"\"has wrong format";
                throw new IllegalArgumentException(err);
            }
        } else {
            return false;
        }
        return true;
    }

    public String Calculate(){
        result = new char[RESULTMAXSIZE+4];
        result[0]='"';
        int i=0, j=0, n=0;
        switch (operand){
            case '+':
                while (i < argstr1.length() || j < argstr2.length() ) {
                    if (i+j+1<RESULTMAXSIZE) {
                        if (i < argstr1.length()) {
                            result[i+1] = argstr1.charAt(i);
                            i++;
                        } else {
                            result[i+j+1]=argstr2.charAt(j);
                            j++;
                        }
                    } else {
                        result[i+j+1] =result[i+j+2]=result[i+j+3]='.';
                        j += 3;
                        break;
                    }
                }
                result[i+j] = '"';
                break;
            case '-':
                if (argstr1.length()<argstr2.length()) {
                    err = "Second Argument more than first";
                    throw new IllegalArgumentException(err);
                }
                String stemp = argstr1.replaceAll(argstr2,""); //? argstr2.length():0;
                for (i = 0; i < stemp.length(); i++) {
                        result[i + 1] = stemp.charAt(i);
                }
                result[i+1] ='"';
                 break;
            case '/':
                for (i = 0; i < argstr1.length()/Integer.valueOf(argstr2) ; i++) {
                    result[i+1] = argstr1.charAt(i);
                }
                result[i+1] ='"';
                break;
            case '*':
                n = argstr1.length();
                Outer:
                for (j = 0;  j< Integer.valueOf(argstr2); j++) {
                    for (i = 0; i < n ; i++) {
                        if (j*n+i+1<RESULTMAXSIZE) {
                            result[j*n+i+1] = argstr1.charAt(i);
                            result[j*n+i+2] = '"';
                        } else{
                            result[j*n+i+1] =result[j*n+i+2]=result[j*n+i+3]='.';
                            result[j*n+i+4]='"';
                            break Outer;
                        }
                    }
                }


                break;
            default:
                err ="Illegilal operand";
                throw new IllegalArgumentException(err);

        }

    return result.toString();
    }
}
