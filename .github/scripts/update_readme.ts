
const version: string | undefined = Deno.env.get('VERSION');

const output: string | undefined = Deno.env.get('OUTPUT');

console.log(`version is: ${version}`);
console.log(`output is: ${output} ... type: ${typeof output}`);

if (output) {

console.log(`version: ` + output.replace("/refs/tags/", ""))
}

console.log(`current directory is : ${Deno.cwd()}`);

if (!version) {
    throw new Error('VERSION environment variable not found.');
}

let readmeContent: string = Deno.readTextFileSync('readme.md');

readmeContent = readmeContent.replace(/:addjs:v\[\d\]+\.\[\d\]+/, `:addjs:${version}`) ;
Deno.writeTextFileSync('readme.md', readmeContent);

