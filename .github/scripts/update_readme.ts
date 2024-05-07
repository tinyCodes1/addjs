const output: string | undefined = Deno.env.get('OUTPUT');


if (output) {
console.log(`output is: ${output.toString()} ... type: ${typeof output}`);
 const version = output.replace("/refs/tags/", "")
console.log(`version: ` + output.replace("/refs/tags/", ""))

console.log(`current directory is : ${Deno.cwd()}`);


let readmeContent: string = Deno.readTextFileSync('readme.md');

readmeContent = readmeContent.replace(/:addjs:v\[\d\]+\.\[\d\]+/, `:addjs:${version}`) ;
Deno.writeTextFileSync('readme.md', readmeContent);
}
