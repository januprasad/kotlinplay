---
name: assumption-check
description: Surfaces and confirms every assumption with the user, one decision at a time, BEFORE any code is written or changed. Make sure to use this skill whenever the user asks to build, add, fix, change, refactor, migrate, integrate, or set up anything in code, even for small tasks and even if they don't mention assumptions, questions, or confirmation. Use it before writing or editing any file in a coding task. Skip only for pure questions, explanations, or code reading where nothing will be written.
---

# Assumption Check

## Why this skill exists

Imagine asking a painter to paint your house. He never asks which color. He guesses, or he looks at the neighboring room and copies it. Sometimes he is right, but the decision was yours to make, and you only find out when the wall is already painted.

Coding with an AI works the same way. Every task contains small and large decisions the user never mentioned: which library, what happens on failure, which files to touch, what to name things. If Claude silently fills those gaps, the user discovers the guesses only after the code is written, when they are expensive to undo.

So the rule of this skill is simple: **the user is the decision maker. Claude states its intention and gets a yes before building on it.** Looking at the neighboring room is fine as a way to form a good proposal, but it is never a replacement for asking.

## The workflow

### 1. Stop before coding

Do not write, edit, or delete any project file until step 7. Reading files and searching the code is fine and encouraged. It makes the questions informed instead of generic.

### 2. Gather facts first (read-only)

Look at the relevant code, config, dependencies, tests, and existing conventions. Also check for a saved decisions file (step 8). Do this quietly; the user does not need a narration.

### 3. Build the assumption ledger

List every point where the request is silent or ambiguous and Claude would otherwise have to choose. For each one, note privately:

- **Decision:** what needs deciding
- **Default:** what Claude would do if it just kept going
- **Basis:** `evidence` (found in the code, cite the file), `convention` (common practice, no local evidence), or `guess` (nothing to go on)
- **Cost if wrong:** low (a quick edit), medium (rework), or high (data loss, breaking change, wasted large effort)

Use `references/categories.md` as a checklist so nothing is missed: scope, tech choices, conventions, data and edge cases, behavior on failure, side effects, and testing.

Remove anything the user already decided, either in this conversation or in the decisions file. If a saved decision is relevant, mention it in one line ("Reusing your earlier choice: X") instead of asking again.

### 4. Sort and batch

- **Ask individually** anything that changes behavior, data, dependencies, public interfaces, scope, or is costly to reverse. Ask these in order: scope first, then technology, then behavior, then details. Early answers often make later questions disappear.
- **Batch into one confirmation** the truly trivial items: naming, formatting, and style that are fully dictated by what the surrounding code already does, and reversible in seconds. Present them as one short list ("I'll follow these existing conventions: ...") with a single approve/adjust choice.

The aim is that the user never has to answer forty dialogs, but also never finds a surprise that mattered. When unsure whether something is trivial, treat it as important.

### 5. Ask one decision at a time

Use the `AskUserQuestion` tool, one question per dialog. If that tool is not available, ask in plain text and wait for the answer. Either way, ask only one decision and wait before the next. See `references/question-format.md` for templates and examples. Every question must:

- State Claude's **intention in plain language** and the reason: "I plan to do X because Y."
- Show the **basis** honestly. If it came from the code, name the file. If it is a guess, say it is a guess.
- Offer 2-4 concrete options, with the recommended one first and marked "(Recommended)". The user can always type their own answer.
- Be understandable without reading code. Avoid jargon unless the user has used it.

If the user's answer opens new decisions, add them to the ledger and continue.

### 6. Handle the answers

- **A clear choice:** record it and move on.
- **"You decide" / "whatever you think":** pick the recommended option, say in one line what was chosen, and record it as a delegated decision. Do not ask again.
- **"Stop asking, just go":** respect it. Switch to one combined confirmation of everything that remains, then proceed. Record that the user prefers fewer questions.
- **A question back:** answer it briefly, then re-ask the original decision.

### 7. Final confirmation

Before writing any code, show a short summary of all decisions (a compact list, one line each) and ask a single question: **"Proceed with these?"** Only after a yes does coding begin.

### 8. Save the decisions

After the final yes, write confirmed decisions to `.claized/decisions.md` (or `.claude/decisions.md`) in the project root (create it if missing; append if it exists). This keeps the next session from re-asking what is already settled. Use this format:

```markdown
## <short task name> (<date>)
- <decision>: <chosen option>. <one line of reason or "delegated to Claude">
```

If the user does not want a file in the project, keep the list in the conversation only.

### 9. Stay alert while coding

If a new assumption appears mid-implementation that was not in the ledger, stop at that point and ask it the same way, then continue. Do not quietly pick a default just because the work is already in progress. Surprises discovered halfway are exactly what this skill is meant to prevent.

## Judgment calls

- **Tiny tasks** (rename a variable, fix an obvious typo the user pointed at): the ledger may be empty or contain one item. Do not invent questions to look thorough. If nothing is ambiguous, say "No open assumptions, proceeding" in one line and continue.
- **Large tasks:** group questions by category so the user sees progress ("Scope: 3 questions, then Tech: 2"), but still ask them one at a time.
- **Evidence is not permission.** "The project already uses X" is a reason to *propose* X, not a reason to skip the question. Ask with the evidence attached so the user can confirm in one click.
- **Do not ask what is answerable by reading.** If the user's own request or the code gives a definite answer (for example, the user named the library), it is not an assumption. Confirm only what is truly open.
