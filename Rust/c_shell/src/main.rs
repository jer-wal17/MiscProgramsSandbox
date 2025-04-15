use std::env;
use std::process;

fn main() {
    let args: Vec<String> = env::args().collect();
    if args.len() < 2 {
        eprintln!("Usage: cargo run -- <command> <add'l args for command>");
        process::exit(1);
    }

    let program = &args[1];

    println!("Parent's ID is {}", process::id());

    if args.len() == 2 {
        if let Ok(mut child) = process::Command::new(program).spawn() {
            println!("Child's ID is {}", child.id());
            child.wait().expect("command wasn't running");
        } else {
            println!("{program} command didn't start");
        }
    } else if args.len() == 3 {
        if let Ok(mut child) = process::Command::new(program).arg(&args[2]).spawn() {
            println!("Child's ID is {}", child.id());
            child.wait().expect("command wasn't running");
        } else {
            println!("{program} command didn't start");
        }
    } else if args.len() > 2 {
        if let Ok(mut child) = process::Command::new(program).args(&args[2..]).spawn() {
            println!("Child's ID is {}", child.id());
            child.wait().expect("command wasn't running");
        } else {
            println!("{program} command didn't start");
        }
    }
}
