# WynncraftIngredientLibrary
非常に使い勝手が悪い素材の探索ライブラリです。
## コードサンプル
```java
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (true) {
            WynnIngredientLib lib = WynnIngredientLib.getLib(); //インスタンスの取得
            String inpt = "";
            inpt = sc.next();
            while (!inpt.equals("END")) {
                lib.filterIngredients(EnumIdentification.valueOf(inpt)); //Identificationによるフィルタリング
                inpt = sc.next();
            }
            for(Ingredient ingredient : lib.getIngredients()){
                System.out.println(ingredient.getName() + " - " + ingredient.searchIdentification(EnumIdentification.AGILITYPOINTS).getMax());
            }
        }

    }
}

```
## 注意
以下のライブラリを使用しています。
- Gson 2.9.1
このライブラリには含まれていないため、各自で追加してください。
