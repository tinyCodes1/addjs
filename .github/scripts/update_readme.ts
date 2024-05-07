
const version: string | undefined = Deno.env.get('VERSION');

const output: string | undefined = Deno.env.get('OUTPUT');

console.log(`version is: ${version}`);
console.log(`output is: ${output} ... type: ${typeof output}`);


if (!version) {
    throw new Error('VERSION environment variable not found.');
}

let readmeContent: string = Deno.readTextFileSync('README.md');

readmeContent = readmeContent.replace(/:addjs:v.\../, `:addjs:${version}`);

Deno.writeTextFileSync('README.md', readmeContent);

