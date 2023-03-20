//
//  ContentView.swift
//  TodoApp
//
//  Created by Yunus A. on 18.03.23.
//

import SwiftUI

struct ContentView: View {
    @State var name: String = ""
    @State var password: String = ""
    @State var showPassword: Bool = false
    
    var body: some View {
        VStack (alignment: .leading, spacing: 15) {
            Spacer()
            TextField("Name",
                      text: $name,
                      prompt: Text("Name").foregroundColor(.blue)) //hallo
            .padding(10)
            .overlay {
                RoundedRectangle(cornerRadius: 10)
                    .stroke(.blue, lineWidth: 2)
            }
            .padding(.horizontal)
            
            HStack{
                Group{
                    if showPassword {
                        TextField("Password",
                                  text: $password, prompt: Text("Password").foregroundColor(.red))
                    }else{
                        SecureField("Password", text: $password,
                                    prompt: Text("Password").foregroundColor(.red))
                    }
                }
                .padding(10)
                .overlay{
                    RoundedRectangle(cornerRadius: 10)
                        .stroke(.red,lineWidth: 2)
                }
            }.padding(.horizontal)
            
            Spacer()
        
        }
    }
}


struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
