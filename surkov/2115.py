from typing import List


class Solution:
    def findAllRecipes(self, recipes: List[str], ingredients: List[List[str]], supplies: List[str]) -> List[str]:
        supplies = set(supplies)
        book = dict(zip(recipes, ingredients))

        visited = set()
        ans = set()

        while len(recipes) > 0:
            curr = recipes[-1]

            is_visited = curr in visited
            if is_visited:
                recipes.pop()
                curr_recipe = book.get(curr, None)
                if curr_recipe is not None and all(ingr in supplies or ingr in ans for ingr in curr_recipe):
                    ans.add(curr)
            else:
                curr_recipe = book.get(curr, None)
                if curr_recipe is not None:
                    for ingr in curr_recipe:
                        if ingr not in supplies and ingr in book and ingr not in ans:
                            recipes.append(ingr)
                visited.add(curr)

        return list(ans)


if __name__ == "__main__":
    sol = Solution()
    sol.findAllRecipes(
        ["ju", "fzjnm", "x", "e", "zpmcz", "h", "q"],
        [["d"], ["hveml", "f", "cpivl"], ["cpivl", "zpmcz", "h", "e", "fzjnm", "ju"],
         ["cpivl", "hveml", "zpmcz", "ju", "h"], ["h", "fzjnm", "e", "q", "x"],
         ["d", "hveml", "cpivl", "q", "zpmcz", "ju", "e", "x"], ["f", "hveml", "cpivl"]],
        ["f", "hveml", "cpivl", "d"]
    )
