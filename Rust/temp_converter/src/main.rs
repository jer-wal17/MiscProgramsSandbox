use std::{io, process::exit};

fn main() {
    println!("Welcome to Temp Converter.");

    println!("Would you like to convert to Celsius (C) or Fahrenheit (F) (Q to quit)? ");

    let mut user_input = String::new();
    let c_or_f;

    let _f_comp: String = "f".to_owned();
    let _c_comp: String = "c".to_owned();

    loop {
        io::stdin()
            .read_line(&mut user_input)
            .expect("Failed to read line");

        let user_input = user_input.trim().to_lowercase();

        if user_input == "f" {
            c_or_f = "F";
            break;
        } else if user_input == "c" {
            c_or_f = "C";
            break;
        } else if user_input == "q" {
            exit(0);
        } else {
            println!("Input not recognized.");
        }
    }

    println!("What temp would you like to convert? ");
    let mut user_input = String::new();
    io::stdin()
        .read_line(&mut user_input)
        .expect("Failed to read line");

    let user_input: u32 = match user_input.trim().parse() {
        Ok(num) => num,
        Err(_) => {
            println!("Please make sure you input a number.");
            0
        }
    };
    if c_or_f == "F" {
        println!(
            "Your converted temp is {} deg Fahrenheit.",
            ((user_input * 9 / 5) + 32)
        );
    } else if c_or_f == "C" {
        println!(
            "Your converted temp is {} deg Celsius.",
            ((user_input - 32) * 5 / 9)
        );
    } else {
        eprintln!("Catastrophic error (internal value was changed)");
        exit(1);
    }
}
